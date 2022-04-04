<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="站点名称">
                <a-input v-model="queryParam.siteName" placeholder="请输入站点名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="配送费">
                <a-input-number v-model="queryParam.shippingFee" :min="0" style="width: 100%"/>
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
    <a-card :bordered="false" >
      <!-- 增加 -->
      <t-distribution-service-add-form
        v-if="showAddModal"
        ref="tDistributionServiceAddForm"
        :receivingId="receivingId"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-distribution-service-edit-form
        v-if="showEditModal"
        ref="tDistributionServiceEditForm"
        :receivingId="receivingId"
        @ok="getList"
        @close="showEditModal = false"
      />
    <div class="table-operations">
        <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tDistributionService:add']">
            <a-icon type="plus" />新增
        </a-button>
        <a-button type="" @click="handleExport" v-hasPermi="['shadd:tDistributionService:export']">
            <a-icon type="download" />导出
        </a-button>
        <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tDistributionService:remove']">
            <a-icon type="delete" />删除
        </a-button>
        <a-tooltip title="刷新">
            <a-icon @click="getList" class="action" :type="loading ? 'loading' : 'reload'" />
        </a-tooltip>
    </div>
      <a-table
        :loading="loading"
        rowKey="id"
        size="middle"
        :columns="columns"
        :data-source="tDistributionServiceList"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :pagination="false">
        <span
          slot="operation"
          slot-scope="text, record">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tDistributionService:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tDistributionService:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tDistributionService:remove']">
            删除
          </a>
        </span>
      </a-table>
    </a-card>
  </div>
</template>
<script>
import { listTDistributionService, delTDistributionService, exportTDistributionService } from '@/api/shadd/tDistributionService'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TDistributionServiceAddForm from '@/views/shadd/tshippingaddress/modules/TDistributionServiceAddForm'
import TDistributionServiceEditForm from '@/views/shadd/tshippingaddress/modules/TDistributionServiceEditForm'
export default {
  name: 'TDistributionService',
    props: {
        receivingId: {
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
    TDistributionServiceAddForm,
    TDistributionServiceEditForm
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
      // 配送服务子表表格数据
      tDistributionServiceList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        site: undefined,
        siteName: undefined,
        shippingFee: undefined

      },
      columns: [
        {
          title: '站点',
          dataIndex: 'siteName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '配送费',
          dataIndex: 'shippingFee',
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
    /** 查询配送服务子表列表 */
    getList () {
      this.loading = true
      this.queryParam.receivingId = this.receivingId
      listTDistributionService(this.queryParam).then(response => {
        this.tDistributionServiceList = response.data.list
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
        site: undefined,
        siteName: undefined,
        shippingFee: undefined

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
                  this.$refs.tDistributionServiceAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tDistributionServiceAddForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tDistributionServiceIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTDistributionService(tDistributionServiceIds)
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
          return exportTDistributionService(that.queryParam)
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
