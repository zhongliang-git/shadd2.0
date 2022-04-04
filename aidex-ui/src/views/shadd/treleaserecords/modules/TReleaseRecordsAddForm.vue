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
          <a-form-model-item label="厂家" prop="dactoryid">
            <a-select
              show-search
              placeholder="请选择厂家"
              @blur="cleanSearchMess"
              :filter-option="setSearchMess"
              @inputKeydown="e => enterKeydown(e)"
              defaultActiveFirstOption={false}
              :value="form.dactoryid"
              v-model="form.dactoryid"
              @change="dactoryChange"
            >
              <a-select-option
                v-for="(d, offerIndex) in this.factoryList"
                :key="offerIndex"
                :value="d.id">
                {{ d.name }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="产品" prop="productid">
              <a-select
                show-search
                placeholder="请选择产品"
                defaultActiveFirstOption={false}
                :value="form.productid"
                v-model="form.productid"
                @change="productChange"
              >
                <a-select-option
                  v-for="(d, offerIndex) in this.productList"
                  :key="offerIndex"
                  :value="d.id">
                  {{ d.productName }}
                </a-select-option>
              </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货站点" prop="siteid">
            <a-select
              show-search
              placeholder="请选择站点"
              defaultActiveFirstOption={false}
              :value="form.siteid"
              v-model="form.siteid"
            >
              <a-select-option
                v-for="(d, offerIndex) in this.siteList"
                :key="offerIndex"
                :value="d.siteId">
                {{ d.siteName }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货数量" prop="receivingnumber">
            <a-input-number v-model="form.receivingnumber" :min="0" style="width: 100%"/>
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
import TReleaseRecordsAddForm from './TReleaseRecordsForm'
export default {
  ...TReleaseRecordsAddForm
}
</script>
