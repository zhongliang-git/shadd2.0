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
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-model-item label="站点" prop="shippingFee">
            <a-select
              show-search
              :filter-option="setSearchMess"
              @inputKeydown="setSiteDataList"
              @blur="cleanSearchMess"
              defaultActiveFirstOption={false}
              :allowClear="form.site"
              placeholder="请选择站点"
              :value="form.site"
              v-model="form.site"
            >
              <a-select-option
                v-for="(d, index) in form.sites"
                :key="index"
                :value="d.id">
                {{ d.name }}
              </a-select-option>
            </a-select>
         </a-form-model-item>
        </a-col>

        <a-col :span="12">
          <a-form-model-item label="配送费" prop="shippingFee">
            <a-input-number v-model="form.shippingFee" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>

        <a-col :span="24">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
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
import TDistributionServiceAddForm from './TDistributionServiceForm'
export default {
  ...TDistributionServiceAddForm
}
</script>
