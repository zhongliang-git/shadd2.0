import AntModal from '@/components/pt/dialog/AntModal'
import { getTOrderDetail, addTOrderDetail, updateTOrderDetail } from '@/api/shadd/tOrderDetail'

export default {
  name: 'CreateForm',
  props: {
    orderId: {
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
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加订单明细子表',
      // 表单参数
      form: {},
      rules: {
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
        productId: undefined,

        price: undefined,

        shippingFee: undefined,

        num: undefined,

        total: undefined

      }
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.open = true
      this.reset()
      this.form.orderId = this.orderId
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tOrderDetailId = row.id
      getTOrderDetail(tOrderDetailId).then(response => {
        this.form = response.data
        this.formTitle = '修改订单明细子表'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTOrderDetail(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTOrderDetail(saveForm).then(response => {
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
      const index = '/shadd/torder/index'
      this.$router.push(index)
    }
  }
}
