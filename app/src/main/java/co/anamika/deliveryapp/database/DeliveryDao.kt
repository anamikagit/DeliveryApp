package co.anamika.deliveryapp.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.anamika.deliveryapp.model.Delivery

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Delivery>)

    @Query("SELECT * FROM deliveries")
    fun reposByName(): DataSource.Factory<Int, Delivery>
}