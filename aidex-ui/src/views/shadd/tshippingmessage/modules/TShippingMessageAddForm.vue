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
        <a-col :span="12" >
        <a-form-model-item label="客户名称" prop="userid">
            <select-user
                    v-model="form.userid"
                    select-model="single"
            />
        </a-form-model-item>
    </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货人电话" prop="phone">
            <a-input v-model="form.phone" placeholder="请输入收货人电话"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货单位" prop="consignee">
            <a-input v-model="form.consignee" placeholder="请输入收货单位"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货地址" prop="addressId">
            <a-select
              show-search
              :filter-option="setSearchMess"
              @inputKeydown="setAddressDataList"
              @blur="cleanSearchMess"
              defaultActiveFirstOption={false}
              :allowClear="form.addressId"
              placeholder="请选择收货地址"
              :value="form.addressId"
              v-model="form.addressId"
            >
              <a-select-option
                v-for="(d, index) in form.addressList"
                :key="index"
                :value="d.id">
                {{ d.name }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="24">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="24">
          <a-form-model-item label="详细地址" prop="address">
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
    <a-row :gutter="32">
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
import TShippingMessageAddForm from './TShippingMessageForm'
export default {
  ...TShippingMessageAddForm
}
</script>
