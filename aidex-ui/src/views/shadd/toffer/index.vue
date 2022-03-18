<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="发货站点">
                <a-input v-model="queryParam.siteid" placeholder="请输入发货站点" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="采购价">
                <a-input-number v-model="queryParam.price" :min="0" style="width: 100%"/>
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
      <t-offer-add-form
        v-if="showAddModal"
        ref="tOfferAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-offer-edit-form
        v-if="showEditModal"
        ref="tOfferEditForm"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="厂家报价"
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
        tableKey="base-tOffer-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tOfferList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tOffer:add']">
            <a-icon type="plus" />新增
          </a-button>
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tOffer:export']">
            <a-icon type="download" />导出
          </a-button>
          <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tOffer:remove']">
            <a-icon type="delete" />删除
          </a-button>
        </div>
        <span slot="operation"  slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tOffer:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tOffer:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tOffer:remove']">
            删除
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTOffer, delTOffer, exportTOffer } from '@/api/shadd/tOffer'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TOfferAddForm from '@/views/shadd/toffer/modules/TOfferAddForm'
import TOfferEditForm from '@/views/shadd/toffer/modules/TOfferEditForm'
export default {
  name: 'TOffer',
  components: {
    AdvanceTable,
    TOfferAddForm,
    TOfferEditForm
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
      // 厂家报价表格数据
      tOfferList: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        siteid: undefined,

        price: undefined

      },
      columns: [
        {
          title: '发货站点',
          dataIndex: 'siteid',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '采购价',
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
    /** 查询厂家报价列表 */
    getList () {
      this.loading = true
      listTOffer(this.queryParam).then(response => {
        this.tOfferList = response.data.list
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
                  this.$refs.tOfferAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showEditModal = true
          this.$nextTick(() => (
                  this.$refs.tOfferEditForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tOfferIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTOffer(tOfferIds)
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
          return exportTOffer(that.queryParam)
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
