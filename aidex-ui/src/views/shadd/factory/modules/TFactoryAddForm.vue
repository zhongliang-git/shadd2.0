<template>
  <ant-modal
    modalWidth="800"
    modalHeight="550"
    :visible="open"
    :modal-title="formTitle"
    :adjust-size="true"
    @cancel="cancel"
  >
    <a-form-model ref="form" :model="form" :rules="rules" slot="content" layout="vertical">
      <a-row :gutter="32">
        <a-col :span="12">
          <a-form-model-item label="区划" prop="district">
            <a-cascader @change="onChangeDistrictCode" v-model="form.districtCode" :options="citys" placeholder="请选择"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="联系人" prop="linkman">
            <a-input v-model="form.linkman" placeholder="请输入联系人"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="手机号" prop="mobile">
            <a-input v-model="form.mobile" placeholder="请输入手机号"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="厂家名称" prop="name">
            <a-input v-model="form.name" placeholder="请输入厂家名称"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="厂家图片" prop="name">
            <a-upload
              name="file"
              :beforeUpload="beforeUpload"
              list-type="picture-card"
              :customRequest="e => uploadImage(e)"
              :showUploadList="false">
              <a-button icon="upload">选择图片</a-button>
            </a-upload>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item prop="name">
            <div class="avatar-upload-preview" :style="previews.url? 'width: 100%; height:100%' : 'width: 0px; height:0px;' ">
              <img :src="previews.url" :style="previews.url? 'width: 100%; height:100%' : 'width: 0px; height:0px;' "/>
            </div>
          </a-form-model-item>
        </a-col>

        <a-col :xs="24" :md="12" style="display: none">
          <vue-cropper
            ref="cropper"
            :img="options.img"
            :info="true"
            :autoCrop="options.autoCrop"
            :autoCropWidth="options.autoCropWidth"
            :autoCropHeight="options.autoCropHeight"
            :fixedBox="options.fixedBox"
            @realTime="realTime"
          >
          </vue-cropper>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="24">
          <a-form-model-item label="采购产品" prop="purchaseList">
            <a-button style="margin-left: 10px;margin-bottom: 10px;" @click="purchaseAdd">
              新增采购产品
            </a-button>
            <a-table :columns="purchaseColumns" :data-source="form.purchaseList">
              <template
                v-for="col in ['product', 'exw', 'image', 'imageDetails']"
                :slot="col"
                slot-scope="text, purchaseData, purchaseIndex"
              >
                <div :key="col">
                  <div v-if="col === 'exw'">
                    <a-input-number
                      :min="0"
                      v-model="purchaseData.exw"
                      v-if="purchaseData.editable"
                      style="margin: -5px 0"
                    />
                    <template v-else>
                      {{ text }}
                    </template>
                  </div>
                  <div v-else-if="col === 'product'">
                    <a-select
                      show-search
                      :allowClear="purchaseData.editable"
                      placeholder="请选择产品"
                      option-filter-prop="children"
                      :filter-option="setSearchMess"
                      @inputKeydown="e => setProductDataList(e,purchaseIndex)"
                      @blur="cleanSearchMess"
                      @focus="setProductDataList({key: 'Enter'}, purchaseIndex)"
                      @change="e => changeProductData(e,purchaseIndex)"
                      :disabled="!purchaseData.editable"
                      :value="purchaseData.productid"
                    >
                      <a-select-option
                        v-for="(d, productDataIndex) in purchaseData.productDataList"
                        :key="productDataIndex"
                        :value="d.id">
                        {{ d.minerals }} |  {{ d.specification }}
                      </a-select-option>
                    </a-select>
                  </div>
                  <div v-else-if="col === 'image'">
                    <a-upload
                      :disabled="!purchaseData.editable"
                      name="avatar"
                      list-type="picture-card"
                      class="avatar-uploader"
                      :show-upload-list="false"
                      :beforeUpload="e => purchaseImageBeforeUpload(e, purchaseIndex)"
                    >
                      <img style="width: 100px;height: 100px" v-if="purchaseData.image" :src="purchaseData.image" alt="avatar" />
                      <div v-else>
                        <a-icon :type="purchaseData.loading ? 'loading' : 'plus'" />
                        <div class="ant-upload-text">
                          Upload
                        </div>
                      </div>
                    </a-upload>
                  </div>
                  <div style="width: 100px" v-else-if="col === 'imageDetails'">
                    <a-upload
                      multiple
                      :file-list="purchaseData.imageDetailList"
                      :remove="e => imageDetailsRemove(e, purchaseIndex)"
                      :customRequest="e => imageDetailsCustomRequest(e, purchaseIndex)"
                      :beforeUpload="e => imageDetailsBeforeUpload(e, purchaseIndex)"
                    >
                      <a-button> <a-icon type="upload" /> Upload </a-button>
                    </a-upload>
                  </div>
                </div>
              </template>
              <template slot="operation" slot-scope="text, purchaseData,purchaseIndex">
                <div class="editable-row-operations">
                  <div v-if="purchaseData.editable">
                    <span>
                      <a @click="() => purchaseEdit(purchaseIndex, purchaseData, false)">保存</a>
                    </span>
                    <span style="margin-left: 10px"  @click="purchaseDelete(purchaseIndex)">
                      <a>删除</a>
                    </span>
                  </div>
                  <div v-else>
                      <div>
                        <span @click="purchaseAdd">
                          <a>新增采购</a>
                        </span>
                        <span style="float: right; margin-right: 10px"  @click="purchaseEdit(purchaseIndex,purchaseData,true)">
                          <a>修改</a>
                        </span>
                      </div>
                      <div style="margin-top: 20px">
                        <span @click="addOffer(purchaseIndex)">
                          <a>新增采购价</a>
                        </span>
                        <span style="float: right; margin-right: 10px" @click="purchaseDelete(purchaseIndex)">
                          <a>删除</a>
                        </span>
                      </div>
                  </div>
                </div>
              </template>
              <div slot="expandedRowRender" slot-scope="purchaseData, purchaseIndex" style="margin: 0">
                <a-table :columns="offerColumns" :data-source="purchaseData.offers">
                  <template
                    v-for="col in ['siteid', 'price']"
                    :slot="col"
                    slot-scope="text, offerModel, offerIndex">
                    <div :key="col">
                      <div v-if="col === 'price'">
                        <a-input-number
                          :min="0"
                          v-model="offerModel.price"
                          @change="updatePageData"
                          :disabled="!offerModel.editable"
                          style="margin: -5px 0"
                        />
                      </div>
                      <div v-else-if="col === 'siteid'">
                        <a-select
                          show-search
                          :allowClear="offerModel.editable"
                          placeholder="请选择站点"
                          :filter-option="setSearchMess"
                          defaultActiveFirstOption={false}
                          @inputKeydown="e => setSiteDataList(e,purchaseIndex)"
                          @blur="cleanSearchMess"
                          @focus="setSiteDataList({key: 'Enter'}, purchaseIndex, offerIndex)"
                          @change="e => changeSiteData(e,purchaseIndex,offerIndex)"
                          :disabled="!offerModel.editable"
                          :value="offerModel.siteid"
                          v-model="offerModel.siteid"
                        >
                          <a-select-option
                            v-for="(d, offerIndex) in offerModel.sites"
                            :key="offerIndex"
                            :value="d.id">
                            {{ d.name }}
                          </a-select-option>
                        </a-select>
                      </div>
                    </div>
                  </template>
                  <template slot="operation" slot-scope="text, offerModel, offerIndex">
                    <div class="editable-row-operations">
                     <div v-if="offerModel.editable">
                      <span>
                        <a @click="() => offerEdit(purchaseIndex,offerIndex,offerModel, false)">保存</a>
                      </span>
                      <span style="margin-left: 10px"  @click="offerDelete(purchaseIndex,offerIndex)">
                        <a>删除</a>
                      </span>
                     </div>
                      <div v-else>
                          <span @click="offerEdit(purchaseIndex,offerIndex,offerModel,true)">
                            <a>修改</a>
                          </span>
                        <span style="margin-left: 10px"  @click="offerDelete(purchaseIndex,offerIndex)">
                            <a>删除</a>
                        </span>
                        <span style="margin-left: 10px"  @click="addOffer(purchaseIndex)">
                            <a style="font-size: 20px;color: #2f54eb">+</a>
                          </span>
                      </div>
                    </div>
                  </template>
                </a-table>
              </div>
            </a-table>
        </a-form-model-item>
        </a-col>
        <a-col :span="24">
          <a-form-model-item label="详细地址" prop="coordinate">
            <a-input v-model="form.address" placeholder=""/>
            <template>
              <div>
                <a-row :gutter="16">
                  <a-col :span="12">
                    <a-form-item v-if="map.isAdd" label="关键词">
                      <a-input v-model="map.keyword"/>
                    </a-form-item>
                  </a-col>
                  <a-col :span="12">
                    <a-form-item v-if="map.isAdd" label="地区">
                      <a-input v-model="map.location"/>
                    </a-form-item>
                  </a-col>
                </a-row>
                <baidu-map @ready="mapHandler" style="width: 100%;height: 400px" class="map" :center="map.center" :zoom="15">
                  <bm-marker :position="map.center" :dragging="true" animation="BMAP_ANIMATION_BOUNCE">
                    <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
                    <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
                    <bm-local-search v-if="map.isAdd" class="search" :keyword="map.keyword" :auto-viewport="true" :location="map.location"></bm-local-search>
                  </bm-marker>
                </baidu-map>
              </div>
            </template>
          </a-form-model-item>
        </a-col>
      </a-row>
    </a-form-model>
    <template slot="footer">
      <a-button @click="cancel">
        取消
      </a-button>
      <a-button type="primary" @click="submitForm">
        保存
      </a-button>
    </template>
  </ant-modal>
</template>
<script>
import TFactoryAddForm from './TFactoryForm'
export default {
  ...TFactoryAddForm
}
</script>
<style scoped>
.map {
  width: 100%;
  height: 600px
}
.map .search{
  margin-bottom: 200px
}
</style>
