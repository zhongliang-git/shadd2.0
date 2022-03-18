import AntModal from '@/components/pt/dialog/AntModal'
import { getTSite, addTSite, updateTSite, checkCodeUnique } from '@/api/shadd/tSite'

export default {
  name: 'CreateForm',
  props: {
 },
  components: {
    AntModal
 },
  data () {
    const validateCode = (rule, value, callback) => {
      if (value === '' || value === undefined || value === null) {
        callback(new Error('站点编码不允许为空'))
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
      formTitle: '添加站点',
      // 表单参数
      form: {},
      rules: {
        name: [{ required: true, message: '站点名称不能为空', trigger: 'blur' }],

        code: [{ required: true, message: '站点编码不能为空', validator: validateCode, trigger: 'blur' }],

        zoning: [{ required: true, message: '区划不能为空', trigger: 'blur' }],

        remark: [{ required: true, message: '备注不能为空', trigger: 'blur' }]

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
        name: undefined,

        code: undefined,

        zoning: undefined,

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
      const tSiteId = row.id
      getTSite(tSiteId).then(response => {
        this.form = response.data
        this.formTitle = '修改站点'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
            const saveForm = JSON.parse(JSON.stringify(this.form))
			if (this.form.id !== undefined) {
				updateTSite(saveForm).then(response => {
					this.$message.success('新增成功', 3)
					this.open = false
					this.$emit('ok')
					this.$emit('close')
				})
              } else {
				addTSite(saveForm).then(response => {
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
      const index = '/shadd/tsite/index'
      this.$router.push(index)
    }
  }
}
