package com.luisenricke.hackchiapas.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Suppress("unused")
interface BaseDAO<X> {
    /**
     * Insert a row in the table.
     *
     * @param row: the object to be inserted.
     * @return id of the row inserted.
     */
    @Insert
    fun insert(row: X): Long

    /**
     * Insert a list of objects with vararg to the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    fun inserts(vararg rows: X): List<Long>

    /**
     * Insert a list of objects to the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    fun inserts(rows: List<X>): List<Long>

    /**
     * Count rows from the table.
     *
     * ``@Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")``
     *
     * @return the total number of rows.
     */
    fun count(): Int

    /**
     * Get a list of objects existing in the table.
     *
     * ``@Query("SELECT * FROM ${SCHEMA.TABLE}")``
     *
     * @return list of objects requested.
     */
    fun get(): List<X>

    /**
     * Drop all rows existing in the table.
     *
     * ``@Query("DELETE FROM ${SCHEMA.TABLE}")``
     */
    fun drop()

    interface UpdateDAO<Y> {
        /**
         * Update a row with an existing ID in the table and change the data.
         *
         * @param row: the object to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun update(row: Y): Int

        /**
         * Update a list of objects with vararg which contains existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun updates(vararg rows: Y): Int

        /**
         * Update a list of rows with an existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun updates(rows: List<Y>): Int
    }

    interface DeleteDAO<Y> {
        /**
         * Delete a row from the table.
         *
         * @param row: the object to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun delete(row: Y): Int

        /**
         * Delete a list of rows with vararg from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun deletes(vararg rows: Y): Int

        /**
         * Delete a list of rows from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun deletes(rows: List<Y>): Int
    }

    interface OperationsPrimaryKeyDAO<Y> {

        /**
         * Get a object existing in the table by ID.
         *
         * ``@Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")``
         *
         * @param id: the id of the searched object.
         * @return the object requested.
         */
        fun get(id: Int): Y

        /**
         * Get a list of object existing in the table by ID's.
         *
         *  ``@Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")``
         *
         * @param ids: the list id's of the searched objects.
         * @return the list objects requested.
         *
         */
        fun get(ids: LongArray): List<Y>

        /**
         * Delete a row existing in the table by ID.
         *
         * ``@Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")``
         *
         * @param id: the id of the searched object.
         * @return the total number of rows dropped.
         */
        fun delete(id: Int): Int
    }

    @Deprecated("This interfaces its just a reference query.")
    /**
     * @param <Y> is a child table.
     * @param <Z> is a parent table.
     */
    interface OperationsForeignKeyDAO<Y, Z> {
        /**
         * Count rows from the table Y in Z.
         *
         * ``@Query("SELECT COUNT(*) FROM  ${Y.SCHEMA.TABLE}  AS CHILD INNER JOIN  ${Z.SCHEMA.TABLE}  AS PARENT ON CHILD.parent_id = PARENT.id")``
         *
         * @return the total number of rows.
         */
        fun countChildJoin(): Long

        /**
         * Count rows from the table by ID of the reference.
         *
         * ``@Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE + " WHERE parent_id = :fk")``
         *
         * @param fk: the id of the reference table.
         * @return the total number of rows.
         */
        fun countChild(fk: Int): Long

        /**
         * Count rows from the table Y in Z filtering with foreign key.
         *
         * ``@Query("SELECT COUNT(*) FROM  ${Y.SCHEMA.TABLE}  AS CHILD"``
         *
         * `` + "INNER JOIN  ${Z.SCHEMA.TABLE}  AS PARENT"``
         *
         * ``+ " ON CHILD.parent_id = PARENT.id"``
         *
         * ``+ " WHERE CHILD.parent_id = :fk")``
         *
         * @param fk: the id of the reference table.
         * @return the total number of rows.
         */
        fun countChildJoin(fk: Int): Long

        /**
         * Get a list of objects existing in the table Y in Z.
         *
         * ``@Query("SELECT CHILD.* FROM " + Y.SCHEMA.TABLE + " AS CHILD"``
         *
         * ``+ " INNER JOIN " + Z.SCHEMA.TABLE + " AS PARENT"``
         *
         * ``+ " ON CHILD.parent_id = PARENT.id ")``
         *
         * @return the list of objects requested.
         */
        fun getChildJoin(): List<Y>

        /**
         * Get a list of objects existing in the table by ID of the reference.
         *
         * ``@Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE parent_id = :fk")``
         *
         * @param fk: the id of the reference table.
         * @return the list of objects requested.
         */
        fun getChild(fk: Int): List<Y>

        /**
         * Count rows from the table Y in Z filtering with foreign key
         *
         * ``@Query("SELECT CHILD.* FROM " + Y.SCHEMA.TABLE + " AS CHILD"``
         *
         * ``+ " INNER JOIN " + Z.SCHEMA.TABLE + " AS PARENT"``
         *
         * ``+ " ON CHILD.parent_id = PARENT.id"``
         *
         * ``+ " WHERE CHILD.parent_id = :fk")``
         *
         * @param fk: the id of the reference table.
         * @return the list of objects requested.
         */
        fun getChildJoin(fk: Int): List<Y>

        /**
         * Drop all rows existing in the table by ID of the reference.
         *
         * ``@Query("DELETE FROM " + SCHEMA.TABLE + " WHERE parent_id = :fk")``
         *
         * @param fk: the id of the reference table.
         */
        fun drop(fk: Int)
    }

    interface InnerJoinDAO<A, B> {
        /**
         * Get a list of objects in the left table.
         *
         * ``@Query("SELECT * FROM  ${SCHEMA.TABLE} WHERE left_id = :idLeft" ``
         *
         * @param idLeft: the id of the left table.
         * @return the list of objects matches.
         */
        fun getLeft(idLeft: Int): List<A>

        /**
         * Get a list of objects in the right table.
         *
         * ``@Query("SELECT * FROM  ${SCHEMA.TABLE} WHERE left_id = :idRight" ``
         *
         * @param idRight: the id of the right table.
         * @return the list of objects matches.
         */
        fun getRight(idRight: Int): List<B>

        /**
         * Get a list of objects in the left table existing in the right table.
         *
         * ``@Query("SELECT * FROM  TABLEA " +
         *
         * "INNER JOIN ${SCHEMA.TABLE}  " +
         *
         * "ON TABLEA.id = ${SCHEMA.TABLE}.TABLEA_id " +
         *
         * "WHERE ${SCHEMA.TABLE}.TABLEB_id = :idRight")``
         *
         * @param idRight: the id of the right table.
         * @return the list of objects matches.
         */
        fun getLeftJoinRight(idRight: Int): List<A>

        /**
         * Get a list of objects in the right table existing in the left table.
         *
         * ``@Query("SELECT * FROM  TABLEB " +
         *
         * "INNER JOIN ${SCHEMA.TABLE}  " +
         *
         * "ON TABLEB.id = ${SCHEMA.TABLE}.TABLEB_id " +
         *
         * "WHERE ${SCHEMA.TABLE}.TABLEA_id = :idLeft")``
         *
         * @param idLeft: the id of the right table.
         * @return the list of objects matches.
         */
        fun getRightJoinLeft(idLeft: Int): List<B>
    }
}