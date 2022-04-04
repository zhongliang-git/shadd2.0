import AntModal from '@/components/pt/dialog/AntModal'
import { getTDistributionService, addTDistributionService, updateTDistributionService } from '@/api/shadd/tDistributionService'
import { listTSite } from '@/api/shadd/tSite'

export default {
  name: 'CreateForm',
  props: {
    receivingId: {
        type: String,
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
      searchSiteMess: undefined,
      siteDataList: [],
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加配送服务子表',
      // 表单参数
      form: {},
      rules: {
        site: [{ required: true, message: '站点不能为空', trigger: 'blur' }],
        shippingFee: [{ required: true, message: '配送费不能为空', trigger: 'blur' }]
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
        site: undefined,
        remark: undefined,
        sites: [],
        shippingFee: undefined
      }
      this.getSiteDataList()
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.open = true
      this.reset()
      this.form.receivingId = this.receivingId
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tDistributionServiceId = row.id
      getTDistributionService(tDistributionServiceId).then(response => {
        this.form = response.data
        this.formTitle = '修改配送服务子表'
        this.spinning = !this.spinning
        if (!response.data.sites) {
          this.getSiteDataList()
        }
      })
    },
    setSearchMess(input, option) {
      this.searchSiteMess = input
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    setSiteDataList(e) {
      if (e.key !== 'Enter') {
        return
      }
      this.getSiteDataList()
    },
    cleanSearchMess() {
      this.searchSiteMess = undefined
    },
    getSiteDataList() {
      const queryParam = {
        pageNum: 1,
        pageSize: 10,
        minerals: this.searchProductMess
      }
      listTSite(queryParam).then(response => {
        this.form.sites = response.data.list
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTDistributionService(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTDistributionService(saveForm).then(response => {
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
      const index = '/shadd/tshippingaddress/index'
      this.$router.push(index)
    }
  }
}
