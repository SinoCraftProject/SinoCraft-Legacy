package lq2007.gradle.mod_src_gen;

public interface ITriConsumer<A, B, C> {

    void consume(A a, B b, C c);

    interface RegistryObjectInfoConsumer extends ITriConsumer<String, String, String> {
        @Override
        void consume(String type, String name, String id);
    }
}
