import AntModal from '@/components/pt/dialog/AntModal'
import { getTOrder, addTOrder, updateTOrder } from '@/api/shadd/tOrder'
import SelectUser from '@/components/pt/selectUser/SelectUser'

export default {
  name: 'CreateForm',
  props: {
    billstatusOptions: {
      type: Array,
      required: true
    }
 },
  components: {
    AntModal,
    SelectUser
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
      formTitle: '添加订单',
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
        userId: undefined,

        shippingaddress: undefined,

        dispatchingdate: undefined,

        total: undefined,

        billStatus: undefined

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
      const tOrderId = row.id
      getTOrder(tOrderId).then(response => {
          response.data.userId = { ids: response.data.userId, names: response.data.userIdName }
        this.form = response.data
        this.formTitle = '修改订单'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.userId !== undefined) {
				saveForm.userId = this.form.userId.ids
			}
			if (this.form.id !== undefined) {
				updateTOrder(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTOrder(saveForm).then(response => {
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
