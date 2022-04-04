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
          <a-form-model-item label="铁路运线" prop="lineid">
            <a-select
              show-search
              placeholder="请选择铁路运线"
              @blur="cleanSearchMess"
              defaultActiveFirstOption={false}
              :filter-option="setSearchMess"
              @inputKeydown="e => enterKeydown(e)"
              :value="form.lineid"
              v-model="form.lineid"
            >
              <a-select-option
                v-for="(d, index) in this.lineList"
                :key="index"
                :value="d.id">
                {{ d.name + "(" + d.code + ")" }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="到货数量" prop="number">
            <a-input-number v-model="form.number" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12" >
          <a-form-model-item label="运输状态" prop="billstatus">
            <a-select
              placeholder="请选择运输状态"
              v-model="form.billstatus"
              :getPopupContainer="
                triggerNode => {
                  return triggerNode.parentNode || document.body
                }
              "
              style="width: 100%"
              allow-clear>
              <a-select-option v-for="(d, index) in billstatusOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
            </a-select>
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
import TGoodsTransportAddForm from './TGoodsTransportForm'
export default {
  ...TGoodsTransportAddForm
}
</script>
