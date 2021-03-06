/*

    Copyright 2015 Barend Garvelink

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 */
package nl.garvelink.iban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Ensures that the {@link IBAN} class accepts IBAN numbers from every participating country (...known at the time the test was last updated).
 */
@RunWith(Parameterized.class)
public class IBANFieldsTest {

    private final String plain;
    private final String bankIdentifier;
    private final String branchIdentifier;

    @SuppressWarnings("unused")
    public IBANFieldsTest(String testName, String sepa, String plain, String bankIdentifier, String branchIdentfier, String pretty) {
        this.plain = plain;
        this.bankIdentifier = bankIdentifier;
        this.branchIdentifier = branchIdentfier;
    }

    @Parameterized.Parameters(name = " {0} ")
    public static List<String[]> parameters() {
        return CountryCodesParameterizedTest.PARAMETERS;
    }

    @Test
    public void shouldExtractBankIdentifier() {
        IBAN iban = IBAN.parse(plain);
        assertNotNull(iban);
        if (bankIdentifier != null) {
            assertThat(IBANFields.getBankIdentifier(iban).get(), is(equalTo(bankIdentifier)));
            assertThat(IBANFieldsCompat.getBankIdentifier(iban), is(equalTo(bankIdentifier)));
        } else {
            assertThat(IBANFields.getBankIdentifier(iban).isPresent(), is(false));
            assertThat(IBANFieldsCompat.getBankIdentifier(iban), is(nullValue()));
        }
    }

    @Test
    public void shouldExtractBranchIdentifier() {
        IBAN iban = IBAN.parse(plain);
        assertNotNull(iban);
        if (branchIdentifier != null) {
            assertThat(IBANFields.getBranchIdentifier(iban).get(), is(equalTo(branchIdentifier)));
            assertThat(IBANFieldsCompat.getBranchIdentifier(iban), is(equalTo(branchIdentifier)));
        } else {
            assertThat(IBANFields.getBranchIdentifier(iban).isPresent(), is(false));
            assertThat(IBANFieldsCompat.getBranchIdentifier(iban), is(nullValue()));
        }
    }
}
