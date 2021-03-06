/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.spatial.pending.jts;

import java.io.IOException;

import org.apache.lucene.util.BytesRef;

import com.spatial4j.core.exception.InvalidShapeException;
import com.vividsolutions.jts.io.InStream;

public class BytesRefStream implements InStream {
  protected BytesRef ref;
  protected int off;

  public BytesRefStream(BytesRef ref) {
    setBytesRef(ref);
  }

  public void setBytesRef(BytesRef ref) {
    this.ref= ref;
    this.off = ref.offset;
  }
  public BytesRef getBytesRef() {
    return this.ref;
  }

  @Override
  public void read(byte[] buf) throws IOException {
    if ( buf.length > ref.length+off ) {
      throw new InvalidShapeException("Asking for too many bytes");
    }
    System.arraycopy(ref.bytes, off, buf, 0, buf.length);
    off += buf.length;
  }
}
