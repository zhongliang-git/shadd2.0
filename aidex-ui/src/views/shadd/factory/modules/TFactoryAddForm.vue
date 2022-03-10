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
            <a-upload name="file" :beforeUpload="beforeUpload" :showUploadList="false">
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
            <a-table :columns="purchaseColumns" :data-source="form.purchaseList" bordered>
            <template
              v-for="col in ['product', 'exw', 'site', 'price', 'image']"
              :slot="col"
              slot-scope="text, record, index"
            >
              <div :key="col">
                <div v-if="col !== 'image'">
                  <a-input
                           v-if="record.editable"
                           style="margin: -5px 0"
                           :value="text"
                           @change="e => purchaseChange(e.target.value, col, index)"
                  />
                  <template v-else>
                    {{ text }}
                  </template>
                </div>
                <div v-else>
                  <a-upload
                    name="avatar"
                    list-type="picture-card"
                    class="avatar-uploader"
                    :show-upload-list="false"
                    action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                    :before-upload="uploadProductImage"
                    @change="e => purchaseChange(e, col, index)"
                  >
                    <img style="width: 100px;height: 100px" v-if="record.image" :src="record.image" alt="avatar" />
                    <div v-else>
                      <a-icon :type="record.loading ? 'loading' : 'plus'" />
                      <div class="ant-upload-text">
                        Upload
                      </div>
                    </div>
                  </a-upload>
                </div>
              </div>
            </template>
            <template slot="operation" slot-scope="text, record,index">
              <div class="editable-row-operations">
                <span v-if="record.editable">
                  <a @click="() => purchaseEdit(index,record, false)">保存</a>
                </span>
                <dev v-else>
                  <span @click="purchaseEdit(index,record,true)">
                    <a>修改</a>
                  </span>
                  <span style="margin-left: 10px"  @click="purchaseDelete(index)">
                    <a>删除</a>
                  </span>
                </dev>
              </div>
            </template>
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
                <baidu-map @ready="mapHandler" style="width: 100%;height: 200px" class="map" :center="map.center" :zoom="15">
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
  height: 200px
}
.map .search{
  margin-bottom: 200px
}
</style>
