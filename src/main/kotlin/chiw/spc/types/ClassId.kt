package chiw.spc.types

enum class ClassId(val classId: Int) {
    Order(1),
    Commodity(2);

    fun getClassIdValue(): Int {
        return this.classId;
    }

    companion object {
        fun getClassIdValue(name: String): Int {
            try {
                return valueOf(name).getClassIdValue()
            } catch (e: Exception) {
                throw e;
            }
        }
    }
}