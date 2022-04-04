<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="客户名称">
                <a-input v-model="queryParam.userName" placeholder="请输入客户名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="邀请码">
                <a-input v-model="queryParam.code" placeholder="请输入邀请码" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col>
              <span class="table-page-search-submitButtons" style="float: right;">
                <a-button type="primary" @click="handleQuery"><a-icon type="search" />查询</a-button>
                <a-button style="margin-left: 8px" @click="resetQuery"><a-icon type="redo" />重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
    </a-card>
    <a-card :bordered="false" class="table-card">
      <!-- 增加 -->
      <t-invitation-code-add-form
        v-if="showAddModal"
        ref="tInvitationCodeAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <advance-table
        title="邀请码"
        :pagination="{
          current: queryParam.pageNum,
          pageSize: queryParam.pageSize,
          total: total,
          showSizeChanger: true,
          showLessItems: true,
          showQuickJumper: true,
          showTotal: (total, range) => `第 ${range[0]}-${range[1]} 条，总计 ${total} 条`,
          onChange: changeSize,
          onShowSizeChange: onShowSizeChange
        }"
        tableKey="base-tInvitationCode-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tInvitationCodeList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="createInvitationCode" v-hasPermi="['shadd:tInvitationCode:add']">
            <a-icon type="plus" />生成邀请码
          </a-button>
        </div>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { create, listTInvitationCode, delTInvitationCode, exportTInvitationCode } from '@/api/shadd/tInvitationCode'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TInvitationCodeAddForm from '@/views/shadd/tinvitationcode/modules/TInvitationCodeAddForm'
import TInvitationCodeEditForm from '@/views/shadd/tinvitationcode/modules/TInvitationCodeEditForm'
export default {
  name: 'TInvitationCode',
  components: {
    AdvanceTable,
    TInvitationCodeAddForm,
    TInvitationCodeEditForm
  },
  data () {
    return {
      showAddModal: false,
      showEditModal: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 选中的主键集合
      selectedRowKeys: [],
      // 选中的数据集合
      selectedRows: [],
      // 高级搜索 展开/关闭
      advanced: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // label的百分比
      labelCol: { span: 6 },
      // 内容区域的百分比
      wrapperCol: { span: 18 },
      // 邀请码表格数据
      tInvitationCodeList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        code: undefined,

        userid: undefined,

        userName: undefined

      },
      columns: [
        {
          title: '邀请码',
          dataIndex: 'code',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '客户',
          dataIndex: 'userName',
          align: 'left',
          width: '10%'
        }
      ]
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询邀请码列表 */
    getList () {
      this.loading = true
      listTInvitationCode(this.queryParam).then(response => {
        this.tInvitationCodeList = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        pageNum: 1,
        pageSize: 10,
        code: undefined,

        userid: undefined

      }
      this.handleQuery()
    },
    createInvitationCode() {
      create().then(response => {
        this.getList()
      })
    },
    /** 翻页操作 */
    onShowSizeChange (current, pageSize) {
      this.queryParam.pageSize = pageSize
      this.getList()
    },
    /** 翻页操作 */
    onSizeChange (current, size) {
        this.queryParam.pageNum = 1
        this.queryParam.pageSize = size
        this.getList()
    },
    /** 翻页操作 */
    changeSize (current, pageSize) {
      this.queryParam.pageNum = current
      this.queryParam.pageSize = pageSize
      this.getList()
    },
    /** 翻页操作 */
    onSelectChange (selectedRowKeys, selectedRows) {
        this.selectedRowKeys = selectedRowKeys
        this.selectedRows = selectedRows
        this.ids = this.selectedRows.map(item => item.id)
        this.single = selectedRowKeys.length !== 1
        this.multiple = !selectedRowKeys.length
    },
    /** 查询折叠和展开操作 */
    toggleAdvanced () {
        this.advanced = !this.advanced
    },
    handleAdd () {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tInvitationCodeAddForm.handleAdd()
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tInvitationCodeIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTInvitationCode(tInvitationCodeIds)
            .then(() => {
              that.onSelectChange([], [])
              that.getList()
              that.$message.success(
                '删除成功',
                3
              )
          })
        },
        onCancel () {}
      })
    },
    /** 导出按钮操作 */
    handleExport () {
      var that = this
      this.$confirm({
        title: '是否确认导出?',
        content: '此操作将导出当前条件下所有数据而非选中数据',
        onOk () {
          return exportTInvitationCode(that.queryParam)
            .then(response => {
              that.download(response.msg)
              that.$message.success(
                '导出成功',
                3
              )
          })
        },
        onCancel () {}
      })
    }
  }
}
</script>
