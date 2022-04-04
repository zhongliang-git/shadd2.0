<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="名称">
                <a-input v-model="queryParam.name" placeholder="请输入名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="区划">
                <a-input v-model="queryParam.district" placeholder="请输入区划" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="详情地址">
                <a-input v-model="queryParam.address" placeholder="请输入详情地址" allow-clear @keyup.enter.native="handleQuery"/>
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
      <t-shipping-address-add-form
        v-if="showAddModal"
        ref="tShippingAddressAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-shipping-address-edit-form
        v-if="showEditModal"
        ref="tShippingAddressEditForm"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="收货地址"
        :expandedRowKeys="expandedKeys"
        @expand="onExpandCurrent"
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
        tableKey="base-tShippingAddress-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tShippingAddressList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tShippingAddress:add']">
            <a-icon type="plus" />新增
          </a-button>
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tShippingAddress:export']">
            <a-icon type="download" />导出
          </a-button>
          <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tShippingAddress:remove']">
            <a-icon type="delete" />删除
          </a-button>
        </div>
    <span
        slot="expandedRowRender"
        slot-scope="{ record}"
    >
        <t-distribution-service-index
                ref="TDistributionServiceIndex"
                title="子表"
                :receivingId="record.id"
        />
    </span>
        <span slot="operation"  slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tShippingAddress:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tShippingAddress:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tShippingAddress:remove']">
            删除
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTShippingAddress, delTShippingAddress, exportTShippingAddress } from '@/api/shadd/tShippingAddress'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TShippingAddressAddForm from '@/views/shadd/tshippingaddress/modules/TShippingAddressAddForm'
import TShippingAddressEditForm from '@/views/shadd/tshippingaddress/modules/TShippingAddressEditForm'
import TDistributionServiceIndex from '@/views/shadd/tshippingaddress/TDistributionServiceIndex'
export default {
  name: 'TShippingAddress',
  components: {
    AdvanceTable,
	  TDistributionServiceIndex,
    TShippingAddressAddForm,
    TShippingAddressEditForm
  },
  data () {
    return {
      showAddModal: false,
      showEditModal: false,
      expandedKeys: undefined,
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
      // 收货地址表格数据
      tShippingAddressList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,

        district: undefined,

        address: undefined

      },
      columns: [
        {
          title: '名称',
          dataIndex: 'name',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '区划',
          dataIndex: 'district',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '详情地址',
          dataIndex: 'address',
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
    /** 查询收货地址列表 */
    getList () {
      this.loading = true
      listTShippingAddress(this.queryParam).then(response => {
        this.tShippingAddressList = response.data.list
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
        name: undefined,

        district: undefined,

        address: undefined

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
                  this.$refs.tShippingAddressAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tShippingAddressAddForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tShippingAddressIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTShippingAddress(tShippingAddressIds)
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
          return exportTShippingAddress(that.queryParam)
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
    },
    onExpandCurrent (expandedKeys, row) {
      if (expandedKeys) {
        this.expandedKeys = [row.id]
      } else {
        this.expandedKeys = []
      }
    }
  }
}
</script>
