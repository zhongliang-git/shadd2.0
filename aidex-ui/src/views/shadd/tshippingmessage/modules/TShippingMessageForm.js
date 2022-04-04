import AntModal from '@/components/pt/dialog/AntModal'
import { getTShippingMessage, addTShippingMessage, updateTShippingMessage } from '@/api/shadd/tShippingMessage'
import SelectUser from '@/components/pt/selectUser/SelectUser'
import { citys } from '@/views/shadd/component/city/City.js'
import { listTShippingAddress } from '@/api/shadd/tShippingAddress'

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
      citys,
      open: false,
      spinning: false,
      delayTime: 100,
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      loading: false,
      total: 0,
      id: undefined,
      formTitle: '添加收货信息',
      // 表单参数
      form: {},
      rules: {
        district: [{ required: true, message: '区划不能为空', trigger: 'blur' }],

        address: [{ required: true, message: '详情地址不能为空', trigger: 'blur' }],

        userid: [{ required: true, message: '客户名称不能为空', trigger: 'blur' }],

        phone: [{ required: true, message: '收货人电话不能为空', trigger: 'blur' }],

        consignee: [{ required: true, message: '收货单位不能为空', trigger: 'blur' }],

        addressId: [{ required: true, message: '收货单位不能为空', trigger: 'blur' }]

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
        district: undefined,
        districtCode: undefined,
        address: undefined,

        userid: undefined,

        phone: undefined,

        consignee: undefined,

        remark: undefined,

        addressList: [],

        addressId: undefined

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
      this.getAddressDataList()
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tShippingMessageId = row.id
      getTShippingMessage(tShippingMessageId).then(response => {
        response.data.userid = { ids: response.data.userid, names: response.data.useridName }
        response.data.districtCode = JSON.parse(response.data.districtCode)
        this.form = response.data
        this.formTitle = '修改收货信息'
        this.spinning = !this.spinning
        if (!this.form.addressList) {
          this.getAddressDataList(this.form.addressId)
          this.searchId = undefined
        }
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
          const saveForm = JSON.parse(JSON.stringify(this.form))
          saveForm.districtCode = JSON.stringify(saveForm.districtCode)
          if (this.form.userid !== undefined) {
            saveForm.userid = this.form.userid.ids
          }
          if (this.form.id !== undefined) {
            updateTShippingMessage(saveForm).then(response => {
              this.$message.success('新增成功', 3)
              this.open = false
              this.$emit('ok')
              this.$emit('close')
            })
          } else {
            addTShippingMessage(saveForm).then(response => {
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
      const index = '/shadd/tshippingmessage/index'
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
    },
    setSearchMess(input, option) {
      this.searchSiteMess = input
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    setAddressDataList(e) {
      if (e.key !== 'Enter') {
        return
      }
      this.getAddressDataList()
    },
    cleanSearchMess() {
      this.searchSiteMess = undefined
    },
    getAddressDataList (searchId) {
      const queryParam = {
        pageNum: 1,
        pageSize: 10,
        id: searchId,
        name: this.searchProductMess
      }
      listTShippingAddress(queryParam).then(response => {
        this.form.addressList = response.data.list
      })
    }
  }
}
