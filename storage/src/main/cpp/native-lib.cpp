#include <jni.h>
#include <string>
#include <android/log.h>
/**
 * 存放token
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_wmc_sp_SPUtils_putToken(JNIEnv *env, jobject thiz, jstring path, jstring token) {
    //获取文件对象
    const char *filePath=env->GetStringUTFChars(path,0);
    FILE *file = fopen(filePath, "w");
    //获取token
    const char *content = env->GetStringUTFChars(token, 0);
    //写入文件
    fputs(content, file);
    fclose(file);
}

/**
 * 获取token
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_wmc_sp_SPUtils_getToken(JNIEnv *env, jobject thiz, jstring path) {
    //获取文件
    const char *filePath=env->GetStringUTFChars(path,0);
    FILE *file = fopen(filePath, "r");
    char content[1024];
    //读取内容
    fgets(content,1004,file);
    fclose(file);
    return env->NewStringUTF(content);
}