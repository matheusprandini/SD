/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package operacoesgrafo;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-05-18")
public class Grafo implements org.apache.thrift.TBase<Grafo, Grafo._Fields>, java.io.Serializable, Cloneable, Comparable<Grafo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Grafo");

  private static final org.apache.thrift.protocol.TField V_FIELD_DESC = new org.apache.thrift.protocol.TField("V", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField A_FIELD_DESC = new org.apache.thrift.protocol.TField("A", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new GrafoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new GrafoTupleSchemeFactory();

  public java.util.List<Vertice> V; // required
  public java.util.List<Aresta> A; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    V((short)1, "V"),
    A((short)2, "A");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // V
          return V;
        case 2: // A
          return A;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.V, new org.apache.thrift.meta_data.FieldMetaData("V", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "Vertice"))));
    tmpMap.put(_Fields.A, new org.apache.thrift.meta_data.FieldMetaData("A", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "Aresta"))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Grafo.class, metaDataMap);
  }

  public Grafo() {
  }

  public Grafo(
    java.util.List<Vertice> V,
    java.util.List<Aresta> A)
  {
    this();
    this.V = V;
    this.A = A;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Grafo(Grafo other) {
    if (other.isSetV()) {
      java.util.List<Vertice> __this__V = new java.util.ArrayList<Vertice>(other.V.size());
      for (Vertice other_element : other.V) {
        __this__V.add(other_element);
      }
      this.V = __this__V;
    }
    if (other.isSetA()) {
      java.util.List<Aresta> __this__A = new java.util.ArrayList<Aresta>(other.A.size());
      for (Aresta other_element : other.A) {
        __this__A.add(other_element);
      }
      this.A = __this__A;
    }
  }

  public Grafo deepCopy() {
    return new Grafo(this);
  }

  @Override
  public void clear() {
    this.V = null;
    this.A = null;
  }

  public int getVSize() {
    return (this.V == null) ? 0 : this.V.size();
  }

  public java.util.Iterator<Vertice> getVIterator() {
    return (this.V == null) ? null : this.V.iterator();
  }

  public void addToV(Vertice elem) {
    if (this.V == null) {
      this.V = new java.util.ArrayList<Vertice>();
    }
    this.V.add(elem);
  }

  public java.util.List<Vertice> getV() {
    return this.V;
  }

  public Grafo setV(java.util.List<Vertice> V) {
    this.V = V;
    return this;
  }

  public void unsetV() {
    this.V = null;
  }

  /** Returns true if field V is set (has been assigned a value) and false otherwise */
  public boolean isSetV() {
    return this.V != null;
  }

  public void setVIsSet(boolean value) {
    if (!value) {
      this.V = null;
    }
  }

  public int getASize() {
    return (this.A == null) ? 0 : this.A.size();
  }

  public java.util.Iterator<Aresta> getAIterator() {
    return (this.A == null) ? null : this.A.iterator();
  }

  public void addToA(Aresta elem) {
    if (this.A == null) {
      this.A = new java.util.ArrayList<Aresta>();
    }
    this.A.add(elem);
  }

  public java.util.List<Aresta> getA() {
    return this.A;
  }

  public Grafo setA(java.util.List<Aresta> A) {
    this.A = A;
    return this;
  }

  public void unsetA() {
    this.A = null;
  }

  /** Returns true if field A is set (has been assigned a value) and false otherwise */
  public boolean isSetA() {
    return this.A != null;
  }

  public void setAIsSet(boolean value) {
    if (!value) {
      this.A = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case V:
      if (value == null) {
        unsetV();
      } else {
        setV((java.util.List<Vertice>)value);
      }
      break;

    case A:
      if (value == null) {
        unsetA();
      } else {
        setA((java.util.List<Aresta>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case V:
      return getV();

    case A:
      return getA();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case V:
      return isSetV();
    case A:
      return isSetA();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Grafo)
      return this.equals((Grafo)that);
    return false;
  }

  public boolean equals(Grafo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_V = true && this.isSetV();
    boolean that_present_V = true && that.isSetV();
    if (this_present_V || that_present_V) {
      if (!(this_present_V && that_present_V))
        return false;
      if (!this.V.equals(that.V))
        return false;
    }

    boolean this_present_A = true && this.isSetA();
    boolean that_present_A = true && that.isSetA();
    if (this_present_A || that_present_A) {
      if (!(this_present_A && that_present_A))
        return false;
      if (!this.A.equals(that.A))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetV()) ? 131071 : 524287);
    if (isSetV())
      hashCode = hashCode * 8191 + V.hashCode();

    hashCode = hashCode * 8191 + ((isSetA()) ? 131071 : 524287);
    if (isSetA())
      hashCode = hashCode * 8191 + A.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Grafo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetV()).compareTo(other.isSetV());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetV()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.V, other.V);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetA()).compareTo(other.isSetA());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetA()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.A, other.A);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Grafo(");
    boolean first = true;

    sb.append("V:");
    if (this.V == null) {
      sb.append("null");
    } else {
      sb.append(this.V);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("A:");
    if (this.A == null) {
      sb.append("null");
    } else {
      sb.append(this.A);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GrafoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GrafoStandardScheme getScheme() {
      return new GrafoStandardScheme();
    }
  }

  private static class GrafoStandardScheme extends org.apache.thrift.scheme.StandardScheme<Grafo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Grafo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // V
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.V = new java.util.ArrayList<Vertice>(_list0.size);
                Vertice _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new Vertice();
                  _elem1.read(iprot);
                  struct.V.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setVIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // A
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
                struct.A = new java.util.ArrayList<Aresta>(_list3.size);
                Aresta _elem4;
                for (int _i5 = 0; _i5 < _list3.size; ++_i5)
                {
                  _elem4 = new Aresta();
                  _elem4.read(iprot);
                  struct.A.add(_elem4);
                }
                iprot.readListEnd();
              }
              struct.setAIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Grafo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.V != null) {
        oprot.writeFieldBegin(V_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.V.size()));
          for (Vertice _iter6 : struct.V)
          {
            _iter6.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.A != null) {
        oprot.writeFieldBegin(A_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.A.size()));
          for (Aresta _iter7 : struct.A)
          {
            _iter7.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GrafoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GrafoTupleScheme getScheme() {
      return new GrafoTupleScheme();
    }
  }

  private static class GrafoTupleScheme extends org.apache.thrift.scheme.TupleScheme<Grafo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Grafo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetV()) {
        optionals.set(0);
      }
      if (struct.isSetA()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetV()) {
        {
          oprot.writeI32(struct.V.size());
          for (Vertice _iter8 : struct.V)
          {
            _iter8.write(oprot);
          }
        }
      }
      if (struct.isSetA()) {
        {
          oprot.writeI32(struct.A.size());
          for (Aresta _iter9 : struct.A)
          {
            _iter9.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Grafo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list10 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.V = new java.util.ArrayList<Vertice>(_list10.size);
          Vertice _elem11;
          for (int _i12 = 0; _i12 < _list10.size; ++_i12)
          {
            _elem11 = new Vertice();
            _elem11.read(iprot);
            struct.V.add(_elem11);
          }
        }
        struct.setVIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.A = new java.util.ArrayList<Aresta>(_list13.size);
          Aresta _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new Aresta();
            _elem14.read(iprot);
            struct.A.add(_elem14);
          }
        }
        struct.setAIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

