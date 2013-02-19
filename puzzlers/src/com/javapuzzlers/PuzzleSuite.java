package com.javapuzzlers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.javapuzzlers.expressive.ExpressiveTest;

@RunWith(Suite.class)
@SuiteClasses(value = { ExpressiveTest.class })
public class PuzzleSuite {
}
