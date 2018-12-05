# TinkerPatchHotFix  
生成补丁文件，上传至Tinker Platform平台进行更新  
点击Gradle -> build -> assebleRelease生成release apk（路径：app->build->bakapk）  
修改bug  
点击Gradle -> tinker -> tinkerPatchRelease生成patch_signed_7zip.apk补丁（路径：app->build->outputs->apk->tinkerPatch）  
将补丁文件，上传至tinker platform平台http://tinkerpatch.com/
