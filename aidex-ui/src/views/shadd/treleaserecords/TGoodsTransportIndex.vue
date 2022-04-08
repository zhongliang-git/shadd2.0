<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="铁路运线">
                <a-input v-model="queryParam.lineName" placeholder="请输入铁路运线" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="到货数量">
                <a-input-number v-model="queryParam.number" :min="0" style="width: 100%"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="运输状态" prop="billstatus">
                <a-select placeholder="请选择运输状态" v-model="queryParam.billstatus" style="width: 100%" allow-clear>
                  <a-select-option v-for="(d, index) in billstatusOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
                </a-select>
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
      <t-goods-transport-add-form
        v-if="showAddModal"
        ref="tGoodsTransportAddForm"
        :billstatusOptions="billstatusOptions"
        :relid="relid"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-goods-transport-edit-form
        v-if="showEditModal"
        ref="tGoodsTransportEditForm"
        :billstatusOptions="billstatusOptions"
        :relid="relid"
        @ok="getList"
        @close="showEditModal = false"
      />
    <div class="table-operations">
        <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tGoodsTransport:add']">
            <a-icon type="plus" />新增
        </a-button>
        <a-button type="" @click="handleExport" v-hasPermi="['shadd:tGoodsTransport:export']">
            <a-icon type="download" />导出
        </a-button>
        <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tGoodsTransport:remove']">
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
        :data-source="tGoodsTransportList"
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
import { listTGoodsTransport, delTGoodsTransport, exportTGoodsTransport } from '@/api/shadd/tGoodsTransport'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TGoodsTransportAddForm from '@/views/shadd/treleaserecords/modules/TGoodsTransportAddForm'
import TGoodsTransportEditForm from '@/views/shadd/treleaserecords/modules/TGoodsTransportEditForm'
export default {
  name: 'TGoodsTransport',
    props: {
        relid: {
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
    TGoodsTransportAddForm,
    TGoodsTransportEditForm
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
      // 货物运输子表表格数据
      tGoodsTransportList: [],
      // 运输状态字典
      billstatusOptions: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        relid: undefined,

        lineName: undefined,

        number: undefined,

        lineid: undefined,

        billstatus: undefined

      },
      columns: [
        {
          title: '铁路运线',
          dataIndex: 'lineName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '到货数据',
          dataIndex: 'number',
          align: 'right',
          width: '10%'
        },
        {
          title: '运输状态',
          dataIndex: 'billstatus',
          scopedSlots: { customRender: 'billstatus' },
          align: 'center',
          width: '10%'
        },
        {
          title: '创建日期',
          dataIndex: 'createTime',
          scopedSlots: { customRender: 'createTime' },
          align: 'center',
          width: '10%'
        },
        {
          title: '修改日期',
          dataIndex: 'updateTime',
          scopedSlots: { customRender: 'updateTime' },
          align: 'center',
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
    this.queryParam.relid = this.relid
    this.getList()
    this.getDicts('transport_status').then(response => {
      this.billstatusOptions = response.data
    })
  },
  methods: {
    /** 查询货物运输子表列表 */
    getList () {
      this.loading = true
      listTGoodsTransport(this.queryParam).then(response => {
        this.tGoodsTransportList = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    // 运输状态字典翻译
    billstatusFormat (row) {
        if (row.billstatus) {
            return this.selectDictLabel(this.billstatusOptions, row.billstatus)
        } else {
            return ''
        }
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
        relid: undefined,

        number: undefined,

        lineid: undefined,

        billstatus: undefined

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
                  this.$refs.tGoodsTransportAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showEditModal = true
          this.$nextTick(() => (
                  this.$refs.tGoodsTransportEditForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tGoodsTransportIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTGoodsTransport(tGoodsTransportIds)
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
          return exportTGoodsTransport(that.queryParam)
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
