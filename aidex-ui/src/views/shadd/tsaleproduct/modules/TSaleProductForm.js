import AntModal from '@/components/pt/dialog/AntModal'
import { getTSaleProduct, addTSaleProduct, updateTSaleProduct } from '@/api/shadd/tSaleProduct'

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
      formTitle: '添加销售产品',
      // 表单参数
      form: {},
      rules: {
        siteid: [{ required: true, message: '销售站点不能为空', trigger: 'blur' }],

        productid: [{ required: true, message: '产品ID不能为空', trigger: 'blur' }],

        price: [{ required: true, message: '售价不能为空', trigger: 'blur' }]

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
        siteid: undefined,

        productid: undefined,

        price: undefined,

        remark: undefined

      }
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tSaleProductId = row.id
      getTSaleProduct(tSaleProductId).then(response => {
        this.form = response.data
        this.formTitle = '修改销售产品'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTSaleProduct(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTSaleProduct(saveForm).then(response => {
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
      const index = '/shadd/tsaleproduct/index'
      this.$router.push(index)
    }
  }
}
