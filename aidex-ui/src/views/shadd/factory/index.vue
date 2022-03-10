<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="区划">
                <a-input v-model="queryParam.district" placeholder="请输入区划" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="联系人">
                <a-input v-model="queryParam.linkman" placeholder="请输入联系人" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="手机号">
                <a-input v-model="queryParam.mobile" placeholder="请输入手机号" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="厂家名称">
                <a-input v-model="queryParam.name" placeholder="请输入厂家名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col>
              <span class="table-page-search-submitButtons" style="float: right;">
                <a-button type="primary" @click="handleQuery"><a-icon type="search" />查询</a-button>
                <a-button style="margin-left: 8px" @click="resetQuery"><a-icon type="redo" />重置</a-button>
                <a @click="toggleAdvanced" style="margin-left: 8px">
                  {{ advanced ? '收起' : '展开' }}
                  <a-icon :type="advanced ? 'up' : 'down'"/>
                </a>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
    </a-card>
    <a-card :bordered="false" class="table-card">
      <!-- 增加 -->
      <t-factory-add-form
        v-if="showAddModal"
        ref="tFactoryAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-factory-edit-form
        v-if="showEditModal"
        ref="tFactoryEditForm"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="厂家"
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
        tableKey="base-tFactory-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tFactoryList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tFactory:add']">
            <a-icon type="plus" />新增
          </a-button>
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tFactory:export']">
            <a-icon type="download" />导出
          </a-button>
          <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tFactory:remove']">
            <a-icon type="delete" />删除
          </a-button>
        </div>
        <span slot="operation" slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tFactory:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tFactory:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tFactory:remove']">
            删除
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTFactory, delTFactory, exportTFactory, updateStatus } from '@/api/shadd/tFactory'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TFactoryAddForm from '@/views/shadd/factory/modules/TFactoryAddForm'
import TFactoryEditForm from '@/views/shadd/factory/modules/TFactoryEditForm'
export default {
  name: 'TFactory',
  components: {
    AdvanceTable,
    TFactoryAddForm,
    TFactoryEditForm
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
      // 厂家表格数据
      tFactoryList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        district: undefined,

        linkman: undefined,

        mobile: undefined,

        name: undefined

      },
      columns: [
        {
          title: '区划',
          dataIndex: 'district',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '联系人',
          dataIndex: 'linkman',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '手机号',
          dataIndex: 'mobile',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '厂家名称',
          dataIndex: 'name',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '操作',
          dataIndex: 'operation',
          align: 'center',
          width: '10%',
          scopedSlots: { customRender: 'operation' }
        }
      ]
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询厂家列表 */
    getList () {
      this.loading = true
      listTFactory(this.queryParam).then(response => {
        this.tFactoryList = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    /* 状态修改 */
    confirmHandleStatus (row) {
      const text = row.status === '1' ? '启用' : '停用'
      const successText = text + '成功'
      const failText = text + '发生异常'
      row.status = row.status === '0' ? '1' : '0'
      updateStatus(row.id, row.status).then(() => {
        this.$message.success(
           successText,
           3
         )
		}).catch(function () {
           this.$message.error(
           failText,
           3
        )
      })
    },
    cancelHandleStatus (row) {
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
        district: undefined,

        linkman: undefined,

        mobile: undefined,

        name: undefined

      }
      this.handleQuery()
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
                  this.$refs.tFactoryAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tFactoryAddForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tFactoryIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTFactory(tFactoryIds)
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
          return exportTFactory(that.queryParam)
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
