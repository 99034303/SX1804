#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_test_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_test_MainActivity_test(JNIEnv *env, jobject thiz, jstring path) {
    const char *es = env->GetStringUTFChars(path, 0);
    FILE *file = fopen(es, "w");
    char s[4] = {"ssd"};
    fputs(s, file);
    fclose(file);
}