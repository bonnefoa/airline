/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.dao;

import airline.BaseClass;
import airline.model.Transaction;
import com.google.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * User: sora
 * Date: 7 févr. 2009
 * Time: 16:06:06
 */
public class TransactionDAOTest extends BaseClass {
    private TransactionDAO transactionDAO;

    @Test
    public void testAddOneTransaction() {
        Transaction transaction = new Transaction(
                "Description",
                new Date());
        transactionDAO.addTransaction(transaction);
        List<Transaction> list = transactionDAO.getTransactions();
        assertEquals(1, list.size());
        assertEquals(transaction, list.get(0));
    }

    @Test
    public void testAddTwoTransactions() {
        Transaction transaction = new Transaction(
                "Description",
                new Date());
        Transaction transaction2 = new Transaction(
                "Description22",
                new Date());
        transactionDAO.addTransaction(transaction);
        transactionDAO.addTransaction(transaction2);
        List<Transaction> list = transactionDAO.getTransactions();
        assertEquals(2, list.size());
        assertEquals(transaction, list.get(0));
        assertEquals(transaction2, list.get(1));
    }

    @Inject
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

}
