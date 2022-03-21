import AntModal from '@/components/pt/dialog/AntModal'
import { getTOffer, addTOffer, updateTOffer } from '@/api/shadd/tOffer'

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
      formTitle: '添加厂家报价',
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
      const tOfferId = row.id
      getTOffer(tOfferId).then(response => {
        this.form = response.data
        this.formTitle = '修改厂家报价'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.loading = true
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.stringify(this.form)
            if (this.form.id !== undefined) {
              updateTOffer(saveForm).then(response => {
                this.loading = false
                this.$message.success('修改成功', 3)
                this.open = false
                this.$emit('ok')
                this.$emit('close')
              })
        } else {
              addTOffer(saveForm).then(response => {
                this.loading = false
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
      const index = '/shadd/toffer/index'
      this.$router.push(index)
    }
  }
}
