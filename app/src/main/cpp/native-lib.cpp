#include <jni.h>

#include <authentication.h>
#include <user_actions.h>

std::string jstring_to_stdstring(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_cpptest_MainActivity_authorizeUser(JNIEnv* env, jobject, jint option, jstring email, jstring password) {
    int _option = static_cast<int>(option);
    std::string _email = jstring_to_stdstring(env, email);
    std::string _password = jstring_to_stdstring(env, password);
    return authenticate_user(_option, _email, _password);
}

extern "C" JNIEXPORT jobjectArray JNICALL
Java_com_example_cpptest_MainActivity_databaseQuery(JNIEnv* env, jobject, jint option, jstring beginDate, jstring endDate) {
    int _option = static_cast<int>(option);
    std::string _begin_date = jstring_to_stdstring(env, beginDate);
    std::string _end_date = jstring_to_stdstring(env, endDate);
    //auto result = db_query(_option, _begin_date, _end_date);
    std::vector<std::vector<std::string>> result {{"Hello", "World"}, {"How", "are you?"}};

    jclass array_class = env->FindClass("java/lang/Object");
    jclass string_class = env->FindClass("java/lang/String");

    jobjectArray matrix = env->NewObjectArray((jsize)result.size(), array_class, NULL);
    for (std::size_t i = 0; i < result.size(); ++i) {
        jobjectArray array = env->NewObjectArray((jsize)result[i].size(), string_class, NULL);
        for (std::size_t j = 0; j < result[i].size(); ++j) {
            env->SetObjectArrayElement(array, (jsize)j, env->NewStringUTF(result[i][j].c_str()));
        }
        env->SetObjectArrayElement(matrix, (jsize)i, array);
        env->DeleteLocalRef(array);
    }

    return matrix;
}
