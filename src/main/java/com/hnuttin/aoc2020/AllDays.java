package com.hnuttin.aoc2020;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparingInt;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import com.hnuttin.aoc2020.common.app.AocDay;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

public class AllDays {

	public static void main(String... args) {
		try (ScanResult result = new ClassGraph()
				.enableClassInfo()
				.enableAnnotationInfo()
				.whitelistPackages(AllDays.class.getPackage().getName()).scan()) {

			ClassInfoList classInfos = result.getClassesWithAnnotation(AocDay.class.getName());

			StopWatch stopWatch = StopWatch.createStarted();
			System.out.println("Running all days...");
			classInfos.stream()
					.sorted(comparingInt(AllDays::dayFromClassInfo))
					.forEach(classInfo -> {
						try {
							Class<?> cls = classInfo.loadClass();
							Method meth = cls.getMethod("main", String[].class);
							String[] params = null;
							meth.invoke(null, (Object) params);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			stopWatch.stop();
			System.out.printf("%n...done in %sms!", stopWatch.getTime(TimeUnit.MILLISECONDS));
		}
	}

	private static int dayFromClassInfo(io.github.classgraph.ClassInfo classInfo) {
		return parseInt(classInfo.getSimpleName().substring(3));
	}
}
