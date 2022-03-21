<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">

    </a-card>
    <a-card :bordered="false" >
      <!-- 编辑 -->
      <t-repertory-edit-form
        v-if="showEditModal"
        ref="tRepertoryEditForm"
        :spid="spid"
        @ok="getList"
        @close="showEditModal = false"
      />
    <div class="table-operations">
        <a-tooltip title="刷新">
            <a-icon @click="getList" class="action" :type="loading ? 'loading' : 'reload'" />
        </a-tooltip>
    </div>
      <a-table
        :loading="loading"
        rowKey="id"
        size="middle"
        :columns="columns"
        :data-source="tRepertoryList"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :pagination="false">
        :pagination="false"
        <span slot="status" slot-scope="text, record">
          <a-badge :status="record.status == '0' ? 'processing' : 'error'" :text=" statusFormat(record) " />
        </span>

        <span slot="operation" slot-scope="text, record">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tRepertory:edit']">
            结算
          </a>
        </span>
        </a-table>
    </a-card>
  </div>
</template>
<script>
import { listTRepertory, delTRepertory, exportTRepertory } from '@/api/shadd/tRepertory'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TRepertoryAddForm from '@/views/shadd/tsaleproduct/modules/TRepertoryAddForm'
import TRepertoryEditForm from '@/views/shadd/tsaleproduct/modules/TRepertoryEditForm'
export default {
  name: 'TRepertory',
    props: {
        spid: {
            type: String,
            required: true
        },
        title: {
            type: String,
            default: '子表'
        }
    },
  components: {
    AdvanceTable,
    TRepertoryAddForm,
    TRepertoryEditForm
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
      // 库存子表表格数据
      tRepertoryList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        repertory: undefined,

        total: undefined,

        clsd: undefined

      },
      columns: [
        {
          title: '库存',
          dataIndex: 'repertory',
          align: 'right',
          width: '10%'
        },
        {
          title: '总量',
          dataIndex: 'total',
          align: 'right',
          width: '10%'
        },
        {
          title: '已结算量',
          dataIndex: 'clsd',
          align: 'right',
          width: '10%'
        },
        {
          title: '未结算量',
          dataIndex: 'accountopen',
          align: 'right',
          width: '10%'
        },
        {
          title: '待结算',
          dataIndex: 'obligation',
          align: 'right',
          width: '10%'
        },
        {
          title: '总结算金额',
          dataIndex: 'totalAmount',
          align: 'right',
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
    /** 查询库存子表列表 */
    getList () {
      this.loading = true
      this.queryParam.spid = this.spid
      listTRepertory(this.queryParam).then(response => {
        this.tRepertoryList = response.data.list
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
        repertory: undefined,

        total: undefined,

        clsd: undefined

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
                  this.$refs.tRepertoryAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showEditModal = true
          this.$nextTick(() => (
                  this.$refs.tRepertoryEditForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tRepertoryIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTRepertory(tRepertoryIds)
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
          return exportTRepertory(that.queryParam)
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
