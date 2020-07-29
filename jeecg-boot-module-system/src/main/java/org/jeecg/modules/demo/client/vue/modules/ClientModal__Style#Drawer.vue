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

        <a-form-item label="client" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['client']" placeholder="请输入client"></a-input>
        </a-form-item>
        <a-form-item label="contact" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['contact']" placeholder="请输入contact"></a-input>
        </a-form-item>
        <a-form-item label="tel" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['tel']" placeholder="请输入tel"></a-input>
        </a-form-item>
        <a-form-item label="email" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['email']" placeholder="请输入email"></a-input>
        </a-form-item>
        <a-form-item label="website" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['website']" placeholder="请输入website"></a-input>
        </a-form-item>
        <a-form-item label="city" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['city']" placeholder="请输入city"></a-input>
        </a-form-item>
        <a-form-item label="address" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['address']" placeholder="请输入address"></a-input>
        </a-form-item>
        <a-form-item label="zipCode" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['zipCode']" placeholder="请输入zipCode"></a-input>
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
  
  export default {
    name: "ClientModal",
    components: { 
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
          add: "/client/client/add",
          edit: "/client/client/edit",
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
          this.form.setFieldsValue(pick(this.model,'client','contact','tel','email','website','city','address','zipCode','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'client','contact','tel','email','website','city','address','zipCode','createTime','updateTime'))
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