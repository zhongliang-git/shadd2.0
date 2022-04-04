import AntModal from '@/components/pt/dialog/AntModal'
import { getUserInfo, addUserInfo, updateUserInfo } from '@/api/shadd/userInfo'
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
    return {
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加用户信息',
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
        balance: undefined,

        total: undefined,

        userId: undefined,

        integrate: undefined,

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
      const userInfoId = row.id
      getUserInfo(userInfoId).then(response => {
          response.data.userId = { ids: response.data.userId, names: response.data.userIdName }
        this.form = response.data
        this.formTitle = '修改用户信息'
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
				updateUserInfo(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addUserInfo(saveForm).then(response => {
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
      const index = '/shadd/userinfo/index'
      this.$router.push(index)
    }
  }
}
