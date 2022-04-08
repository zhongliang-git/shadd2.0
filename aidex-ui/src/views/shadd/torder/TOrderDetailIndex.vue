<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="销售产品">
                <a-input v-model="queryParam.productName" placeholder="请输入销售产品" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="单价">
                <a-input-number v-model="queryParam.price" :min="0" style="width: 100%"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="配送费">
                <a-input-number v-model="queryParam.shippingFee" :min="0" style="width: 100%"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="数量">
                <a-input v-model="queryParam.num" placeholder="请输入数量" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="小计">
                <a-input-number v-model="queryParam.total" :min="0" style="width: 100%"/>
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
    <a-card :bordered="false" >
      <!-- 增加 -->
      <t-order-detail-add-form
        v-if="showAddModal"
        ref="tOrderDetailAddForm"
        :orderId="orderId"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-order-detail-edit-form
        v-if="showEditModal"
        ref="tOrderDetailEditForm"
        :orderId="orderId"
        @ok="getList"
        @close="showEditModal = false"
      />
    <div class="table-operations">
        <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tOrderDetail:add']">
            <a-icon type="plus" />新增
        </a-button>
        <a-button type="" @click="handleExport" v-hasPermi="['shadd:tOrderDetail:export']">
            <a-icon type="download" />导出
        </a-button>
        <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tOrderDetail:remove']">
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
        :data-source="tOrderDetailList"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :pagination="false">
        :pagination="false"
        <span slot="status" slot-scope="text, record">
          <a-badge :status="record.status == '0' ? 'processing' : 'error'" :text=" statusFormat(record) " />
        </span>
        <span slot="operation" slot-scope="text, record">
          <a @click="handleUpdate(record)" :disabled="record.billstatus === '到达'" :style="record.billstatus === '到达'?'color: #8c8c8c': ''" v-hasPermi="['shadd:tGoodsTransport:edit']">
            修改
          </a>
        </span>
      </a-table>
    </a-card>
  </div>
</template>
<script>
import { listTOrderDetail, delTOrderDetail, exportTOrderDetail } from '@/api/shadd/tOrderDetail'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TOrderDetailAddForm from '@/views/shadd/torder/modules/TOrderDetailAddForm'
import TOrderDetailEditForm from '@/views/shadd/torder/modules/TOrderDetailEditForm'
export default {
  name: 'TOrderDetail',
    props: {
        orderId: {
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
    TOrderDetailAddForm,
    TOrderDetailEditForm
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
      // 订单明细子表表格数据
      tOrderDetailList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        productName: undefined,

        price: undefined,

        shippingFee: undefined,

        num: undefined,

        total: undefined,

        orderId: undefined

      },
      columns: [
        {
          title: '采购产品',
          dataIndex: 'productName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '单价',
          dataIndex: 'price',
          align: 'right',
          width: '10%'
        },
        {
          title: '配送费',
          dataIndex: 'shippingFee',
          align: 'right',
          width: '10%'
        },
        {
          title: '数量',
          dataIndex: 'num',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '小计',
          dataIndex: 'total',
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
    /** 查询订单明细子表列表 */
    getList () {
      this.loading = true
      this.queryParam.orderId = this.orderId
      listTOrderDetail(this.queryParam).then(response => {
        this.tOrderDetailList = response.data.list
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
        productId: undefined,

        price: undefined,

        shippingFee: undefined,

        num: undefined,

        total: undefined,

        orderId: undefined

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
                  this.$refs.tOrderDetailAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showEditModal = true
          this.$nextTick(() => (
                  this.$refs.tOrderDetailEditForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tOrderDetailIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTOrderDetail(tOrderDetailIds)
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
          return exportTOrderDetail(that.queryParam)
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
