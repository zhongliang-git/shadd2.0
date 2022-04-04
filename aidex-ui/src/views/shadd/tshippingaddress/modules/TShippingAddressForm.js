import AntModal from '@/components/pt/dialog/AntModal'
import { getTShippingAddress, addTShippingAddress, updateTShippingAddress } from '@/api/shadd/tShippingAddress'
import { citys } from '@/views/shadd/component/city/City.js'

export default {
  name: 'CreateForm',
  props: {
 },
  components: {
    AntModal
 },
  data () {
    return {
      citys,
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加收货地址',
      // 表单参数
      form: {},
      rules: {
        name: [{ required: true, message: '名称不能为空', trigger: 'blur' }],

        district: [{ required: true, message: '区划不能为空', trigger: 'blur' }],

        address: [{ required: true, message: '详细不能为空', trigger: 'blur' }]
      },
      map: {
        type: Object
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

        district: undefined,

        latitude: undefined,

        longitude: undefined,

        address: undefined,

        districtCode: undefined,

        remark: undefined

      }
      this.map.isAdd = true
      this.map.center = {
        lng: 104.10194,
        lat: 30.65984
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
      const tShippingAddressId = row.id
      getTShippingAddress(tShippingAddressId).then(response => {
        if (response.data.districtCode) {
          response.data.districtCode = JSON.parse(response.data.districtCode)
        }
        this.form = response.data
        this.formTitle = '修改收货地址'
        this.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
      if (valid) {
        const saveForm = JSON.parse(JSON.stringify(this.form))
        saveForm.districtCode = JSON.stringify(saveForm.districtCode)
        if (this.form.id !== undefined) {
          updateTShippingAddress(saveForm).then(response => {
            this.$message.success('新增成功', 3)
            this.open = false
            this.$emit('ok')
            this.$emit('close')
          })
         } else {
          addTShippingAddress(saveForm).then(response => {
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
    },
    onChangeDistrictCode(value, selectedOptions) {
      let names
      for (const i in selectedOptions) {
        if (!names) {
          names = selectedOptions[i].label
        } else {
          names += '/' + selectedOptions[i].label
        }
      }
      this.form.district = names
    },
    mapHandler ({ BMap, map }) {
      const me = this
      console.log(BMap, map)
      // 鼠标缩放
      map.enableScrollWheelZoom(true)
      // 点击事件获取经纬度
      map.addEventListener('click', function (e) {
        me.form.address = e.cb.z.title
        me.form.latitude = e.point.lat
        me.form.longitude = e.point.lng
        console.log(e.point.lng, e.point.lat)
        me.$emit('select-location', {
          lng: e.point.lng,
          lat: e.point.lat
        })
      })
    }
  }
}
