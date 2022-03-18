import AntModal from '@/components/pt/dialog/AntModal'
import { getTShippingLine, addTShippingLine, updateTShippingLine } from '@/api/shadd/tShippingLine'
import { listTSite } from '@/api/shadd/tSite'

export default {
  name: 'CreateForm',
  props: {
    servetypeOptions: {
      type: Array,
      required: true
    }
 },
  components: {
    AntModal
 },
  data () {
    return {
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加运线',
      // 表单参数
      form: {},
      rules: {
        shippingsiteid: [{ required: true, message: '发货站点不能为空', trigger: 'blur' }],

        seceivingsiteid: [{ required: true, message: '收货站点不能为空', trigger: 'blur' }],

        servetype: [{ required: true, message: '服务类型不能为空', trigger: 'blur' }],

        name: [{ required: true, message: '站线名称不能为空', trigger: 'blur' }],

        code: [{ required: true, message: '站线编码不能为空', trigger: 'blur' }],

        freight: [{ required: true, message: '运费不能为空', trigger: 'blur' }]
      },
      // 收货站点
      seceivingSiteList: [],
      // 发货站点
      shippingSiteList: [],
      // 查询条件
      searchMess: undefined
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
        shippingsiteid: undefined,

        seceivingsiteid: undefined,

        servetype: undefined,

        freight: undefined,

        remark: undefined,

        name: undefined,

        code: undefined

      }
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
      const tShippingLineId = row.id
      getTShippingLine(tShippingLineId).then(response => {
        this.form = response.data
        this.getSiteDataList('shippingsite', this.form.shippingsiteid)
        this.getSiteDataList('seceivingsite', this.form.seceivingsiteid)
        this.formTitle = '修改运线'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTShippingLine(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTShippingLine(saveForm).then(response => {
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
      const index = '/shadd/tshippingline/index'
      this.$router.push(index)
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
