import AntModal from '@/components/pt/dialog/AntModal'
import { getTGoodsTransport, addTGoodsTransport, updateTGoodsTransport } from '@/api/shadd/tGoodsTransport'
import { listTShippingLine } from '@/api/shadd/tShippingLine'

export default {
  name: 'CreateForm',
  props: {
    billstatusOptions: {
      type: Array,
      required: true
    },
    relid: {
        type: String,
        required: true
    }
 },
  components: {
    AntModal
 },
  data () {
    return {
      searchTerm: undefined,
      lineList: [],
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加货物运输子表',
      // 表单参数
      form: {},
      rules: {
        relid: [{ required: true, message: '发货记录不能为空', trigger: 'blur' }],

        number: [{ required: true, message: '到货数据不能为空', trigger: 'blur' }],

        lineid: [{ required: true, message: '铁路运线不能为空', trigger: 'blur' }],

        billstatus: [{ required: true, message: '运输状态不能为空', trigger: 'blur' }]

      }
    }
  },
  filters: {},
  created () {},
  computed: {},
  watch: {},
  mounted () {},
  methods: {
    onClose () {
      this.open = false
      this.reset()
      this.$emit('close')
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
      this.$emit('close')
    },
    // 表单重置
    reset () {
      this.form = {
        id: undefined,

        relid: this.relid,

        number: undefined,

        lineid: undefined,

        billstatus: '途中',

        remark: undefined
      }
      this.searchTerm = undefined
      this.findLineList()
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.open = true
      this.reset()
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tGoodsTransportId = row.id
      getTGoodsTransport(tGoodsTransportId).then(response => {
        this.form = response.data
        this.formTitle = '修改货物运输子表'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTGoodsTransport(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTGoodsTransport(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
			}
        } else {
          return false
        }
      })
    },
    back () {
      const index = '/shadd/treleaserecords/index'
      this.$router.push(index)
    },
    /**
     * 查询运线
     */
    findLineList() {
      const queryParam = {
          pageNum: 1,
          pageSize: 100,
          name: this.searchTerm,
          cod: this.searchTerm
      }
      listTShippingLine(queryParam).then(response => {
        this.lineList = response.data.list
      })
    },
    setSearchMess(input, option) {
      this.searchTerm = input
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    enterKeydown(key) {
      if (key.key !== 'Enter') {
        return
      }
      this.findLineList()
    },
    cleanSearchMess() {
      this.searchTerm = undefined
      this.findLineList()
    }
  }
}
