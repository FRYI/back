<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="sku" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['sku']" placeholder="请输入sku"></a-input>
        </a-form-item>
        <a-form-item label="project" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['project']" placeholder="请输入project"></a-input>
        </a-form-item>
        <a-form-item label="productName" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['productName']" placeholder="请输入productName"></a-input>
        </a-form-item>
        <a-form-item label="supplier" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['supplier']" placeholder="请输入supplier"></a-input>
        </a-form-item>
        <a-form-item label="paramData" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['paramData']" placeholder="请输入paramData"></a-input>
        </a-form-item>
        <a-form-item label="description" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['description']" placeholder="请输入description"></a-input>
        </a-form-item>
        <a-form-item label="photo" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-image-upload isMultiple v-decorator="['photo']"></j-image-upload>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JImageUpload from '@/components/jeecg/JImageUpload'
  
  export default {
    name: "ProductModal",
    components: { 
      JImageUpload,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/product/product/add",
          edit: "/product/product/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'sku','project','productName','supplier','paramData','description','photo','createTime','updateTime'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'sku','project','productName','supplier','paramData','description','photo','createTime','updateTime'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>