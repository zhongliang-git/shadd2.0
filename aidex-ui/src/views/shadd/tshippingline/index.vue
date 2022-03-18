<template>
  <div>
    <a-card :bordered="false" style="margin-bottom: 10px;">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form :labelCol="labelCol" :wrapperCol="wrapperCol" ref="queryForm">
          <a-row :gutter="32">
            <a-col :span="6" >
              <a-form-item label="站线名称">
                <a-input v-model="queryParam.name" placeholder="请输入站线名称" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="站线编码">
                <a-input v-model="queryParam.code" placeholder="请输入站线编码" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="站线编码">
                <a-input v-model="queryParam.billStatus" placeholder="请输入站线编码" allow-clear @keyup.enter.native="handleQuery"/>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="发货站点">
                <a-select
                  show-search
                  allowClear
                  placeholder="发货站点"
                  :filter-option="searchData"
                  @blur="cleanSearchMess"
                  @inputKeydown="e => getSiteDataListByInputKeydown(e,'shippingsite')"
                  @focus="getSiteDataList('shippingsite')"
                  :value="queryParam.shippingsiteid"
                  v-model="queryParam.shippingsiteid"
                >
                  <a-select-option
                    v-for="(d, offerIndex) in this.shippingSiteList"
                    :key="offerIndex"
                    :value="d.id">
                    {{ d.name }}
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :span="6" >
              <a-form-item label="收货站点">
                <a-select
                  show-search
                  allowClear
                  placeholder="收货站点"
                  :filter-option="searchData"
                  @blur="cleanSearchMess"
                  @inputKeydown="e => getSiteDataListByInputKeydown(e,'seceivingsite')"
                  @focus="getSiteDataList('seceivingsite')"
                  :value="queryParam.seceivingsiteid"
                  v-model="queryParam.seceivingsiteid"
                >
                  <a-select-option
                    v-for="(d, offerIndex) in this.seceivingSiteList"
                    :key="offerIndex"
                    :value="d.id">
                    {{ d.name }}
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
·
            <a-col :span="6" >
              <a-form-item label="运费">
                <a-input-number v-model="queryParam.freight" :min="0" style="width: 100%"/>
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
      <t-shipping-line-add-form
        v-if="showAddModal"
        ref="tShippingLineAddForm"
        :servetypeOptions="servetypeOptions"
        @ok="getList"
        @close="showAddModal = false"
      />
      <!-- 编辑 -->
      <t-shipping-line-edit-form
        v-if="showEditModal"
        ref="tShippingLineEditForm"
        :servetypeOptions="servetypeOptions"
        @ok="getList"
        @close="showEditModal = false"
      />
      <advance-table
        title="运线"
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
        tableKey="base-tShippingLine-index-table"
        rowKey="id"
        size="middle"
        @refresh="getList"
        :columns="columns"
        :data-source="tShippingLineList"
        :loading="loading"
        :format-conditions="true"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      >
        <div class="table-operations" slot="button">
          <a-button type="primary" @click="handleAdd" v-hasPermi="['shadd:tShippingLine:add']">
            <a-icon type="plus" />新增
          </a-button>
          <a-button type="" @click="handleExport" v-hasPermi="['shadd:tShippingLine:export']">
            <a-icon type="download" />导出
          </a-button>
          <a-button type="danger" v-if="!multiple" :disabled="multiple" @click="handleDelete" v-hasPermi="['shadd:tShippingLine:remove']">
            <a-icon type="delete" />删除
          </a-button>
        </div>
        <span slot="servetype"  slot-scope="{record}">
          {{ servetypeFormat(record) }}
        </span>
        <span slot="operation"  slot-scope="{text, record}">
          <a @click="handleUpdate(record)" v-hasPermi="['shadd:tShippingLine:edit']">
            修改
          </a>
          <a-divider type="vertical" v-hasPermi="['shadd:tShippingLine:remove']"/>
          <a @click="handleDelete(record)" v-hasPermi="['shadd:tShippingLine:remove']">
            删除
          </a>
        </span>
      </advance-table>
    </a-card>
  </div>
</template>
<script>
import { listTShippingLine, delTShippingLine, exportTShippingLine } from '@/api/shadd/tShippingLine'
import AdvanceTable from '@/components/pt/table/AdvanceTable'
import TShippingLineAddForm from '@/views/shadd/tshippingline/modules/TShippingLineAddForm'
import TShippingLineEditForm from '@/views/shadd/tshippingline/modules/TShippingLineEditForm'
import { listTSite } from '@/api/shadd/tSite'
export default {
  name: 'TShippingLine',
  components: {
    AdvanceTable,
    TShippingLineAddForm,
    TShippingLineEditForm
  },
  data () {
    return {
      // 收货站点
      seceivingSiteList: [],
      // 发货站点
      shippingSiteList: [],
      // 查询条件
      searchMess: undefined,
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
      // 运线表格数据
      tShippingLineList: [],
      // 服务类型字典
      servetypeOptions: [],
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        shippingSiteName: undefined,

        seceivingSiteName: undefined,

        servetype: undefined,

        freight: undefined,

        name: undefined,

        cod: undefined

      },
      columns: [
        {
          title: '站线名称',
          dataIndex: 'name',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '站线编码',
          dataIndex: 'code',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '发货站点',
          dataIndex: 'shippingSiteName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '收货站点',
          dataIndex: 'seceivingSiteName',
          ellipsis: true,
          align: 'left',
          width: '10%'
        },
        {
          title: '服务类型',
          dataIndex: 'servetype',
          scopedSlots: { customRender: 'servetype' },
          align: 'center',
          width: '10%'
        },
        {
          title: '运费',
          dataIndex: 'freight',
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
    this.getDicts('server_type').then(response => {
      this.servetypeOptions = response.data
    })
  },
  methods: {
    /** 查询运线列表 */
    getList () {
      this.loading = true
      listTShippingLine(this.queryParam).then(response => {
        this.tShippingLineList = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    // 服务类型字典翻译
    servetypeFormat (row) {
        if (row.servetype) {
            return this.selectDictLabel(this.servetypeOptions, row.servetype)
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
        shippingsiteid: undefined,

        seceivingsiteid: undefined,

        servetype: undefined,

        freight: undefined

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
                  this.$refs.tShippingLineAddForm.handleAdd()
          ))
    },
    handleUpdate (record, ids) {
          this.showAddModal = true
          this.$nextTick(() => (
                  this.$refs.tShippingLineAddForm.handleUpdate(record, ids)
          ))
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const tShippingLineIds = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        onOk () {
          return delTShippingLine(tShippingLineIds)
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
          return exportTShippingLine(that.queryParam)
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
    getSiteDataList(type, id) {
      const queryParam = {
        pageNum: 1,
        pageSize: 10,
        minerals: this.searchMess,
        id: id
      }
      listTSite(queryParam).then(response => {
        if (type === 'seceivingsite') {
          this.seceivingSiteList = response.data.list
        } else if (type === 'shippingsite') {
          this.shippingSiteList = response.data.list
        } else {
          this.seceivingSiteList = response.data.list
          this.shippingSiteList = response.data.list
        }
      })
    },
    searchData(input, option) {
      this.searchMess = input
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    cleanSearchMess() {
      this.searchMess = undefined
      this.getSiteDataList()
    },
    getSiteDataListByInputKeydown(e, type) {
      if (e.key !== 'Enter') {
        return
      }
      this.getSiteDataList(type)
    }
  }
}
</script>
