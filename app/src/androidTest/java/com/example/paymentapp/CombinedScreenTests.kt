package com.example.paymentapp

import org.junit.runner.RunWith
import org.junit.runners.Suite

// Specify the classes to be tested
@RunWith(Suite::class)
@Suite.SuiteClasses(
    AccountScreenTest::class,
    ConfirmationScreenTest::class,
    LoginScreenTest::class,
    RequestFundsScreenTest::class
)
class AllTestsSuite {
    // This class remains empty; it is used only as a holder for the above annotations
}
