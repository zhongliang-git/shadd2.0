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
          <a-form-model-item label="站线名称" prop="name">
            <a-input v-model="form.name" placeholder="请输入站线名称"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="站线编码" prop="code">
            <a-input v-model="form.code" placeholder="请输入站线编码"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="发货站点" prop="shippingsiteid">
            <a-select
              show-search
              placeholder="发货站点"
              :filter-option="searchData"
              @blur="cleanSearchMess"
              @inputKeydown="e => getSiteDataListByInputKeydown(e,'shippingsite')"
              :value="form.shippingsiteid"
              v-model="form.shippingsiteid"
            >
              <a-select-option
                v-for="(d, offerIndex) in this.shippingSiteList"
                :key="offerIndex"
                :value="d.id">
                {{ d.name }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货站点" prop="seceivingsiteid">
            <a-select
              show-search
              placeholder="收货站点"
              :filter-option="searchData"
              @blur="cleanSearchMess"
              @inputKeydown="e => getSiteDataListByInputKeydown(e,'seceivingsite')"
              :value="form.seceivingsiteid"
              v-model="form.seceivingsiteid"
            >
              <a-select-option
                v-for="(d, offerIndex) in this.seceivingSiteList"
                :key="offerIndex"
                :value="d.id">
                {{ d.name }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12" >
          <a-form-model-item label="服务类型" prop="servetype">
            <a-select
              placeholder="请选择服务类型"
              v-model="form.servetype"
              :getPopupContainer="
                triggerNode => {
                  return triggerNode.parentNode || document.body
                }
              "
              style="width: 100%"
              allow-clear>
              <a-select-option v-for="(d, index) in servetypeOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="运费" prop="freight">
            <a-input-number v-model="form.freight" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
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
import TShippingLineAddForm from './TShippingLineForm'
export default {
  ...TShippingLineAddForm
}
</script>
