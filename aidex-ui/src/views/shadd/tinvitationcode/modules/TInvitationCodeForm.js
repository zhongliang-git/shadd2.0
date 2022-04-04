import AntModal from '@/components/pt/dialog/AntModal'
import { getTInvitationCode, addTInvitationCode, updateTInvitationCode, checkCodeUnique } from '@/api/shadd/tInvitationCode'
import SelectUser from '@/components/pt/selectUser/SelectUser'

export default {
  name: 'CreateForm',
  props: {
 },
  components: {
    AntModal,
    SelectUser
 },
  data () {
    const validateCode = (rule, value, callback) => {
      if (value === '' || value === undefined || value === null) {
        callback(new Error('邀请码不允许为空'))
      } else {
        checkCodeUnique(this.form.id, this.form.code)
        callback()
      }
    }
    return {
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加邀请码',
      // 表单参数
      form: {},
      rules: {
        code: [{ required: true, message: '邀请码不能为空', validator: validateCode, trigger: 'blur' }]

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
        code: undefined,

        userid: undefined,

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
      const tInvitationCodeId = row.id
      getTInvitationCode(tInvitationCodeId).then(response => {
          response.data.userid = { ids: response.data.userid, names: response.data.useridName }
        this.form = response.data
        this.formTitle = '修改邀请码'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.userid !== undefined) {
				saveForm.userid = this.form.userid.ids
			}
			if (this.form.id !== undefined) {
				updateTInvitationCode(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTInvitationCode(saveForm).then(response => {
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
      const index = '/shadd/tinvitationcode/index'
      this.$router.push(index)
    }
  }
}
