package pl.mg6.fte.livecode.dagger

import android.content.Context
import dagger.ObjectGraph
import groovy.transform.CompileStatic

@CompileStatic
public final class Injector {

    private static List<Object> testModules = new ArrayList<>()
    private static ObjectGraph graph

    static void setTestModules(Object... modules) {
        testModules.clear()
        testModules.addAll(Arrays.asList(modules))
        graph = null
    }

    static void inject(Object root) {
        if (graph == null) {
            graph = createGraph()
        }
        graph.inject(root)
    }

    private static ObjectGraph createGraph() {
        List<Object> allModules = new ArrayList<>()
        allModules.add(new AppModule())
        allModules.addAll(testModules)
        return ObjectGraph.create(allModules.toArray())
    }
}
