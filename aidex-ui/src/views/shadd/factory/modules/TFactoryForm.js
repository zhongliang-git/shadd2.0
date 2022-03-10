import AntModal from '@/components/pt/dialog/AntModal'
import { getTFactory, addTFactory, updateTFactory } from '@/api/shadd/tFactory'
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
      formTitle: '添加厂家',
      // 表单参数
      form: {},
      // 图片列表
      fileList: [],
      rules: {
        district: [{ required: true, message: '区划不能为空', trigger: 'blur' }],

        linkman: [{ required: true, message: '联系人不能为空', trigger: 'blur' }],

        mobile: [{ required: true, message: '手机号不能为空', trigger: 'blur' }],

        name: [{ required: true, message: '厂家名称不能为空', trigger: 'blur' }]

      },

      visible: false,
      confirmLoading: false,
      uploading: false,
      options: {
        // img: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
        img: '',
        autoCrop: true,
        autoCropWidth: 200,
        autoCropHeight: 200,
        fixedBox: true
      },
      previews: {},
      purchaseColumns: [
        {
          title: '产品',
          dataIndex: 'product',
          width: '15%',
          scopedSlots: { customRender: 'product' }
        },
        {
          title: '出厂价',
          dataIndex: 'exw',
          width: '15%',
          scopedSlots: { customRender: 'exw' }
        },
        {
          title: '到货站',
          dataIndex: 'site',
          width: '15%',
          scopedSlots: { customRender: 'site' }
        },
        {
          title: '采购价',
          dataIndex: 'price',
          width: '15%',
          scopedSlots: { customRender: 'price' }
        },
        {
          title: '产品图片',
          dataIndex: 'image',
          width: '20%',
          scopedSlots: { customRender: 'image' }
        },
        {
          title: '操作',
          dataIndex: 'operation',
          scopedSlots: { customRender: 'operation' }
        }
      ],
      roductImageIndex: undefined,
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
    onClose() {
      this.open = false
      this.reset()
      this.$emit('close')
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
      this.$emit('close')
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,

        districtCode: undefined,

        district: undefined,

        image: undefined,

        linkman: undefined,

        mobile: undefined,

        name: undefined,

        remark: undefined,

        address: undefined,

        longitude: undefined,

        latitude: undefined,

        purchaseList: []
      }
      this.map.isAdd = true
      this.map.center = {
        lng: 104.10194,
        lat: 30.65984
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true
      this.reset()
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tFactoryId = row.id
      const thls = this
      getTFactory(tFactoryId).then(response => {
        if (response.data.districtCode) {
          response.data.districtCode = JSON.parse(response.data.districtCode)
        }
        if (!response.data.address) {
          response.data.address = undefined
        }
        thls.map.center = {
          lng: response.data.longitude,
          lat: response.data.latitude
        }
        thls.form = response.data
        thls.previews.url = response.data.image
        thls.formTitle = '修改厂家'
        thls.spinning = !this.spinning
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
          const saveForm = JSON.parse(JSON.stringify(this.form))
          saveForm.districtCode = JSON.stringify(saveForm.districtCode)
          if (this.form.id !== undefined) {
            updateTFactory(saveForm).then(response => {
              this.$message.success('新增成功', 3)
              this.open = false
              this.$emit('ok')
              this.$emit('close')
            })
          } else {
            addTFactory(saveForm).then(response => {
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
    back() {
      const index = '/shadd/factory/index'
      this.$router.push(index)
    },
    edit(id) {
      this.visible = true
      this.id = id
      this.options.img = this.avatar
      /* 获取原始头像 */
    },
    close() {
      this.id = null
      this.visible = false
    },
    cancelHandel() {
      this.close()
    },
    changeScale(num) {
      num = num || 1
      this.$refs.cropper.changeScale(num)
    },
    rotateLeft() {
      this.$refs.cropper.rotateLeft()
    },
    rotateRight() {
      this.$refs.cropper.rotateRight()
    },
    beforeUpload(file) {
      const reader = new FileReader()
      // 把Array Buffer转化为blob 如果是base64不需要
      // 转化为base64
      reader.readAsDataURL(file)
      reader.onload = () => {
        this.options.img = reader.result
        this.form.image = reader.result
      }
      // 转化为blob
      // reader.readAsArrayBuffer(file)

      return false
    },
    realTime(data) {
      this.previews = data
    },
    purchaseAdd() {
      const { purchaseList } = this.form
      const newData = {
        product: undefined,
        image: undefined,
        exw: undefined,
        editable: true
      }
      this.form.purchaseList = [...purchaseList, newData]
    },
    purchaseEdit(index, data, status) {
      const newPurchaseList = [...this.form.purchaseList]
      data.editable = status
      newPurchaseList[index] = data
      this.form.purchaseList = newPurchaseList
    },
    purchaseChange(data, column, index) {
      const newPurchaseList = [...this.form.purchaseList]
      if (column === 'image') {
        if (data.file.status === 'uploading') {
          newPurchaseList[index].loading = true
        } else {
          this.getBase64(data.file.originFileObj, imageUrl => {
            newPurchaseList[index][column] = imageUrl
            newPurchaseList[index].loading = false
          })
        }
        return
      } else {
        newPurchaseList[index][column] = data
      }
      this.form.purchaseList = newPurchaseList
    },
    uploadProductImage(file) {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
      if (!isJpgOrPng) {
        this.$message.error('You can only upload JPG file!')
      }
      return isJpgOrPng
    },
    getBase64(img, callback) {
      const reader = new FileReader()
      reader.addEventListener('load', () => callback(reader.result))
      reader.readAsDataURL(img)
    },
    purchaseDelete(index) {
      const newPurchaseList = [...this.form.purchaseList]
      newPurchaseList.splice(index, 1)
      this.form.purchaseList = newPurchaseList
    },
    displayRender({ labels }) {
      return labels[labels.length - 1]
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
