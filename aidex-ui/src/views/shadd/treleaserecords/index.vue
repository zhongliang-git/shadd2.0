<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="厂家">
                <a-input v-model="queryParam.factoryName" placeholder="请输入厂家" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="产品">
                <a-input v-model="queryParam.productName" placeholder="请输入产品" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="收货站点">
                <a-input v-model="queryParam.siteName" placeholder="请输入收货站点" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="收货数量">
                <a-input-number v-model="queryParam.receivingnumber" :min="0" style="width: 100%"/>
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
      <t-release-records-add-form
        v-if="showAddModal"
        ref="tReleaseRecordsAddForm"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-release-records-edit-form
        v-if="showEditModal"
        ref="tReleaseRecordsEditForm"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="发货记录"
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
        tableKey="base-tReleaseRecords-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tReleaseRecordsList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tReleaseRecords:add']">
            <a-icon type="plus" />新增
          </a-button>
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tReleaseRecords:export']">
            <a-icon type="download" />导出
          </a-button>
          <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tReleaseRecords:remove']">
            <a-icon type="delete" />删除
          </a-button>
        </div>
    <span
        slot="expandedRowRender"
        slot-scope="{ record}"
    >
        <t-goods-transport-index
                ref="TGoodsTransportIndex"
                title="子表"
                :relid="record.id"
        />
    </span>
        <span slot="operation"  slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tReleaseRecords:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tReleaseRecords:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tReleaseRecords:remove']">
            删除
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTReleaseRecords, delTReleaseRecords, exportTReleaseRecords } from '@/api/shadd/tReleaseRecords'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TReleaseRecordsAddForm from '@/views/shadd/treleaserecords/modules/TReleaseRecordsAddForm'
import TReleaseRecordsEditForm from '@/views/shadd/treleaserecords/modules/TReleaseRecordsEditForm'
import TGoodsTransportIndex from '@/views/shadd/treleaserecords/TGoodsTransportIndex'
export default {
  name: 'TReleaseRecords',
  components: {
    AdvanceTable,
	  TGoodsTransportIndex,
    TReleaseRecordsAddForm,
    TReleaseRecordsEditForm
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
      // 发货记录表格数据
      tReleaseRecordsList: [{}],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        dactoryid: undefined,

        productid: undefined,

        siteid: undefined,

        receivingnumber: undefined,

        factoryName: undefined,

        productName: undefined,

        siteName: undefined

      },
      columns: [
        {
          title: '厂家',
          dataIndex: 'factoryName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '产品',
          dataIndex: 'productName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '收货站点',
          dataIndex: 'siteName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '收货数量',
          dataIndex: 'receivingnumber',
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
    /** 查询发货记录列表 */
    getList () {
      this.showAddModal = false
      this.showEditModal = false
      this.loading = true
      listTReleaseRecords(this.queryParam).then(response => {
        this.tReleaseRecordsList = response.data.list
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
        dactoryid: undefined,

        productid: undefined,

        siteid: undefined,

        receivingnumber: undefined

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
                  this.$refs.tReleaseRecordsAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tReleaseRecordsAddForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tReleaseRecordsIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTReleaseRecords(tReleaseRecordsIds)
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
          return exportTReleaseRecords(that.queryParam)
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
