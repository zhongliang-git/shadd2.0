import AntModal from '@/components/pt/dialog/AntModal'
import { getTReleaseRecords, addTReleaseRecords,
  updateTReleaseRecords, factoryList, productList } from '@/api/shadd/tReleaseRecords'

export default {
  name: 'CreateForm',
  props: {
 },
  components: {
    AntModal
 },
  data () {
    return {
      // 站点集合
      siteList: [],
      // 厂家集合
      factoryList: [],
      // 产品集合
      productList: [],
      // 查询条件
      searchTerm: undefined,
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加发货记录',
      // 表单参数
      form: {},
      rules: {
        dactoryid: [{ required: true, message: '厂家不能为空', trigger: 'blur' }],

        productid: [{ required: true, message: '产品不能为空', trigger: 'blur' }],

        siteid: [{ required: true, message: '收货站点不能为空', trigger: 'blur' }],

        receivingnumber: [{ required: true, message: '收货数量不能为空', trigger: 'blur' }]

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
        dactoryid: undefined,

        productid: undefined,

        siteid: undefined,

        receivingnumber: undefined,

        remark: undefined
      }
      this.findFactoryList()
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
      const tReleaseRecordsId = row.id
      getTReleaseRecords(tReleaseRecordsId).then(response => {
        this.form = response.data
        this.formTitle = '修改发货记录'
        this.spinning = !this.spinning
        this.findProductList()
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTReleaseRecords(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTReleaseRecords(saveForm).then(response => {
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
      this.findFactoryList()
    },
    cleanSearchMess() {
      this.searchTerm = undefined
      this.findFactoryList()
    },
    findFactoryList() {
      const queryParam = {
          pageNum: 1,
          pageSize: 30,
          name: this.searchTerm
      }
      factoryList(queryParam).then(response => {
        this.factoryList = response.data.list
      })
    },
    findProductList() {
      if (!this.form.dactoryid) {
        return
      }
      const queryParam = {
        pageNum: 1,
        pageSize: 30,
        factoryid: this.form.dactoryid,
        name: this.searchTerm
      }
      productList(queryParam).then(response => {
        this.productList = response.data.list
        for (const index in this.productList) {
          if (this.productList[index].id === this.form.productid) {
            this.siteList = this.productList[index].offerList
          }
        }
      })
      // 产品集合
      // productList: [],
    },
    findSiteList() {
      // 站点集合
      // siteList: [],
    },
    dactoryChange() {
      this.form.productid = undefined
      this.form.siteid = undefined
      this.productList = []
      this.siteList = []
      this.cleanSearchMess()
      this.findProductList()
    },
    productChange() {
      this.form.siteid = undefined
      for (const index in this.productList) {
        if (this.productList[index].id === this.form.productid) {
          this.siteList = this.productList[index].offerList
        }
      }
    }
  }
}
