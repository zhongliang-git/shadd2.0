<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="销售站点">
                <a-input v-model="queryParam.siteName" placeholder="请输入销售站点" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="厂家">
                <a-input v-model="queryParam.factoryName" placeholder="请输入厂家名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="矿种">
                <a-input v-model="queryParam.minerals" placeholder="请输入矿种名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="规格型号">
                <a-input v-model="queryParam.specification" placeholder="请输入规格型号" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="最低售价">
                <a-input-number v-model="queryParam.startPrice" :min="0" style="float:left;width: 100%"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="最高售价">
                <a-input-number v-model="queryParam.endPrice" :min="0" style="float:left;width: 100%"/>
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
      <t-sale-product-add-form
        v-if="showAddModal"
        ref="tSaleProductAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-sale-product-edit-form
        v-if="showEditModal"
        ref="tSaleProductEditForm"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="销售产品"
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
        tableKey="base-tSaleProduct-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tSaleProductList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tSaleProduct:export']">
            <a-icon type="download" />导出
          </a-button>
        </div>
        <span
            slot="expandedRowRender"
            slot-scope="{ record}"
        >
            <t-repertory-index
                    ref="TRepertoryIndex"
                    title="子表"
                    :spid="record.id"
            />
        </span>
        <span slot="image"  slot-scope="{text, record}">
          <img :src="record.image" style="width: 100%;height: 70px;" alt="" />
        </span>
        <span slot="operation"  slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tSaleProduct:edit']">
            修改
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTSaleProduct, delTSaleProduct, exportTSaleProduct } from '@/api/shadd/tSaleProduct'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TSaleProductAddForm from '@/views/shadd/tsaleproduct/modules/TSaleProductAddForm'
import TSaleProductEditForm from '@/views/shadd/tsaleproduct/modules/TSaleProductEditForm'
import TRepertoryIndex from '@/views/shadd/tsaleproduct/TRepertoryIndex'
export default {
  name: 'TSaleProduct',
  components: {
    AdvanceTable,
	  TRepertoryIndex,
    TSaleProductAddForm,
    TSaleProductEditForm
  },
  data () {
    return {
      expandedKeys: [],
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
      // 销售产品表格数据
      tSaleProductList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        siteName: undefined,

        factoryName: undefined,

        minerals: undefined,

        specification: undefined,

        startPrice: undefined,

        endPrice: undefined
      },
      columns: [
        {
          title: '销售站点',
          dataIndex: 'siteName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '厂家',
          dataIndex: 'factoryName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '矿种',
          dataIndex: 'minerals',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '规格型号',
          dataIndex: 'specification',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '图片',
          dataIndex: 'image',
          ellipsis: true,
          align: 'left',
          width: '10%',
          scopedSlots: { customRender: 'image' }
        },
        {
          title: '售价',
          dataIndex: 'price',
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
    /** 查询销售产品列表 */
    getList () {
      this.loading = true
      listTSaleProduct(this.queryParam).then(response => {
        this.tSaleProductList = response.data.list
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
        siteid: undefined,

        productid: undefined,

        price: undefined

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
                  this.$refs.tSaleProductAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showEditModal = true
          this.$nextTick(() => (
                  this.$refs.tSaleProductEditForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tSaleProductIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTSaleProduct(tSaleProductIds)
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
          return exportTSaleProduct(that.queryParam)
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
