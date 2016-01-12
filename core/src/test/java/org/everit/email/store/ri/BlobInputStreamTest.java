/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
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
package org.everit.email.store.ri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.transaction.xa.XAException;

import org.apache.geronimo.transaction.manager.GeronimoTransactionManager;
import org.everit.blobstore.BlobAccessor;
import org.everit.blobstore.BlobReader;
import org.everit.blobstore.mem.MemBlobstore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlobInputStreamTest {

  private MemBlobstore blobStore;
  private GeronimoTransactionManager transactionManager;

  @Before
  public void before() {
    transactionManager = null;
    try {
      transactionManager = new GeronimoTransactionManager(6000);
    } catch (XAException e) {
      throw new RuntimeException(e);
    }
    blobStore = new MemBlobstore(transactionManager);
  }

  @Test
  public void testRead() throws Exception {
    transactionManager.begin();
    String testMsg = "test message !%@-";
    long blobId;
    try (BlobAccessor createBlob = blobStore.createBlob()) {
      byte[] bytes = testMsg.getBytes(StandardCharsets.UTF_8);
      createBlob.write(bytes, 0, bytes.length);
      blobId = createBlob.getBlobId();
    }
    transactionManager.commit();

    transactionManager.begin();
    try (BlobReader readBlob = blobStore.readBlob(blobId);
        BlobInputStream bis = new BlobInputStream(readBlob);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis, "UTF-8"));) {
      String line = br.readLine();
      Assert.assertEquals(testMsg, line);
    }
    transactionManager.commit();
  }
}
