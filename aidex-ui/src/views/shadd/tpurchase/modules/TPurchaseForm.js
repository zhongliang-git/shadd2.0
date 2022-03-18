import AntModal from '@/components/pt/dialog/AntModal'
import { getTPurchase, addTPurchase, updateTPurchase } from '@/api/shadd/tPurchase'

export default {
  name: 'CreateForm',
  props: {
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
      formTitle: '添加采购产品',
      // 表单参数
      form: {},
      rules: {
        image: [{ required: true, message: '主图不能为空', trigger: 'blur' }],

        factoryid: [{ required: true, message: '厂家不能为空', trigger: 'blur' }],

        productid: [{ required: true, message: '产品不能为空', trigger: 'blur' }]

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
        image: undefined,

        factoryid: undefined,

        productid: undefined,

        exw: undefined,

        remark: undefined

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
      const tPurchaseId = row.id
      getTPurchase(tPurchaseId).then(response => {
        this.form = response.data
        this.formTitle = '修改采购产品'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTPurchase(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTPurchase(saveForm).then(response => {
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
      const index = '/shadd/tpurchase/index'
      this.$router.push(index)
    }
  }
}
