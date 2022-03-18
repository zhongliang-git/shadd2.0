import AntModal from '@/components/pt/dialog/AntModal'
import { getTFactory, addTFactory, updateTFactory } from '@/api/shadd/tFactory'
import { uploadFile } from '@/api/shadd/file'
import { citys } from '@/views/shadd/component/city/City.js'
import { selectListTProduct } from '@/api/shadd/tProduct'
import { listTSite } from '@/api/shadd/tSite'

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
          width: '20%',
          scopedSlots: { customRender: 'product' }
        },
        {
          title: '出厂价',
          dataIndex: 'exw',
          width: '15%',
          scopedSlots: { customRender: 'exw' }
        },
        {
          title: '产品图片',
          dataIndex: 'image',
          width: '20%',
          scopedSlots: { customRender: 'image' }
        },
        {
          title: '详情图',
          dataIndex: 'imageDetails',
          width: '20%',
          scopedSlots: { customRender: 'imageDetails' }
        },
        {
          title: '操作',
          dataIndex: 'operation',
          scopedSlots: { customRender: 'operation' }
        }
      ],
      offerColumns: [
        {
          title: '站点',
          dataIndex: 'siteid',
          width: '40%',
          scopedSlots: { customRender: 'siteid' }
        },
        {
          title: '采购价',
          dataIndex: 'price',
          width: '40%',
          scopedSlots: { customRender: 'price' }
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
      },
      productDataList: [],
      searchProductMess: undefined,
      // 查询参数
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        factoryid: undefined,

        productid: undefined,

        exw: undefined,
        siteDataList: []

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
      this.productDataList = undefined
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true
      this.getProductDataList()
      this.getSiteDataList()
      this.reset()
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.open = true
      this.spinning = !this.spinning
      const tFactoryId = row.id
      const thls = this
      this.getProductDataList()
      this.getSiteDataList()
      getTFactory(tFactoryId).then(response => {
        if (response.data.districtCode) {
          response.data.districtCode = JSON.parse(response.data.districtCode)
        }
        if (!response.data.address) {
          response.data.address = undefined
        }
        const purchaseList = response.data.purchaseList
        for (const i in purchaseList) {
          purchaseList[i].productDataList = [purchaseList[i].product]
          purchaseList[i].imageDetailList = JSON.parse(purchaseList[i].imageDetailList)
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
      const thls = this
      this.$refs.form.validate(valid => {
        if (valid) {
          const saveForm = JSON.parse(JSON.stringify(thls.form))
          for (const pi in saveForm.purchaseList) {
            let newImageDetailList = saveForm.purchaseList[pi].imageDetailList
            newImageDetailList = JSON.parse(JSON.stringify(newImageDetailList))
            for (const ni in newImageDetailList) {
              newImageDetailList[ni].name = this.form.purchaseList[pi].imageDetailList[ni].name
            }
            saveForm.purchaseList[pi].imageDetailList = JSON.stringify(newImageDetailList)
          }
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
      const isJpgOrPng = file.type === 'image/jpeg' ||
        file.type === 'image/jpg' ||
        file.type === 'image/png'
      if (!isJpgOrPng) {
        this.$message.error('只能上传jpg/png格式的图片!')
      }
      const isLt2M = file.size / 1024 / 1024 < 5
      if (!isLt2M) {
        this.$message.error('图片不得大于5MB!')
      }
      return isJpgOrPng && isLt2M
    },
    uploadImage(file) {
      const formData = new FormData()
      const thlt = this
      formData.append('file', file.file)
      uploadFile(formData).then(response => {
        if (response) {
          thlt.previews.url = response
          thlt.form.image = response
        }
      })
    },
    realTime(data) {
      this.previews = data
    },
    purchaseAdd() {
      const { purchaseList } = this.form
      const newData = {
        product: {
          id: undefined,
          minerals: undefined,
          specification: undefined,
          offers: []
        },
        image: undefined,
        exw: undefined,
        editable: true,
        imageDetails: [],
        productDataList: this.productDataList
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
      debugger
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
    },

    setSearchMess(input, option) {
      this.searchProductMess = input
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    cleanSearchMess() {
      this.searchProductMess = undefined
      this.getProductDataList()
    },
    setProductDataList(key, index) {
      if (key.key !== 'Enter') {
        return
      }
      if (this.searchProductMess) {
        this.getProductDataList()
      }
      const newPurchaseList = [...this.form.purchaseList]
      newPurchaseList[index].productDataList = this.productDataList
      this.form.purchaseList = newPurchaseList
    },
    /**
     *
     * @param 查询产品
     */
    getProductDataList() {
      const queryParam = {
          pageNum: 1,
          pageSize: 10,
          minerals: this.searchProductMess
      }
      selectListTProduct(queryParam).then(response => {
        if (response.data) {
          this.productDataList = response.data.list
        } else {
          this.productDataList = []
        }
      })
    },
    addOffer(index) {
      let offers = [...this.form.purchaseList][index].offers
      const newOffer = {
        price: undefined,
        siteid: undefined,
        editable: true,
        sites: this.siteDataList
      }
      if (!offers) {
        offers = []
      }
      this.form.purchaseList[index].offers = [...offers, newOffer]
      this.updatePageData()
    },
    updatePageData() {
      const newPurchaseList = [...this.form.purchaseList]
      this.form.purchaseList = newPurchaseList
    },
    offerEdit(purchaseIndex, offerIndex, offerModel, status) {
      const offers = [...this.form.purchaseList][purchaseIndex].offers
      offerModel.editable = status
      offers[offerModel] = offerModel
      this.form.purchaseList[purchaseIndex].offers = offers
      this.updatePageData()
    },
    offerDelete(index, offerIndex) {
      const offers = [...this.form.purchaseList][index].offers
      offers.splice(offerIndex, 1)
      this.form.purchaseList[index].offers = offers
      this.updatePageData()
    },
    getSites({ text, column, record, index }) {
      console.log('text', text)
      console.log(column)
      console.log(record)
      console.log(index)
    },
    changeProductData(e, index) {
      const newPurchaseList = [...this.form.purchaseList]
      newPurchaseList[index].productid = e
      this.form.purchaseList = newPurchaseList
    },
    getSiteDataList() {
      if (this.siteDataList) {
        return this.siteDataList
      }
      const queryParam = {
        pageNum: 1,
        pageSize: 10,
        minerals: this.searchProductMess
      }
      listTSite(queryParam).then(response => {
        this.siteDataList = response.data.list
        return this.siteDataList
      })
    },
    changeSiteData(e, purchaseIndex, offerIndex) {
      const offers = [...this.form.purchaseList][purchaseIndex].offers
      const offer = offers[offerIndex]
      offer.siteid = e
      this.form.purchaseList[purchaseIndex].offers = offers
      this.updatePageData()
    },
    setSiteDataList(key, purchaseIndex, offerIndex) {
      if (key.key !== 'Enter') {
        return
      }
      if (this.searchProductMess) {
        this.getSiteDataList()
      }
      const offers = [...this.form.purchaseList][purchaseIndex].offers
      const offer = offers[offerIndex]
      offer.sites = this.siteDataList
      offers[offerIndex].offer = offer
      this.form.purchaseList[purchaseIndex].offers = offers
      this.updatePageData()
    },
    imageDetailsCustomRequest(e, purchaseIndex) {
      debugger
    },
    purchaseImageBeforeUpload(file, index) {
      const purchaseList = [...this.form.purchaseList]
      const formData = new FormData()
      const thlt = this
      formData.append('file', file)
      uploadFile(formData).then(response => {
        if (response) {
          purchaseList[index].image = response
          thlt.form.purchaseList = purchaseList
          this.updatePageData()
        }
      })
    },
    imageDetailsBeforeUpload(file, index) {
      const purchaseList = [...this.form.purchaseList]
      if (!purchaseList[index].imageDetailList) {
        purchaseList[index].imageDetailList = []
      }
      const formData = new FormData()
      const thlt = this
      formData.append('file', file)
      uploadFile(formData).then(response => {
        if (response) {
          file.status = 'done'
          file.url = response
          const newImageDetailList = [...purchaseList[index].imageDetailList, file]
          thlt.form.purchaseList[index].imageDetailList = newImageDetailList
          this.updatePageData()
        }
      })
      return false
    },
    imageDetailsRemove(file, index) {
      const purchaseList = [...this.form.purchaseList]
      if (!purchaseList[index].imageDetailList) {
        return
      }
      const i = purchaseList[index].imageDetailList.indexOf(file)
      const newImageDetailList = purchaseList[index].imageDetailList.slice()
      newImageDetailList.splice(i, 1)
      this.form.purchaseList[index].imageDetailList = newImageDetailList
      this.updatePageData()
    }
  }
}
