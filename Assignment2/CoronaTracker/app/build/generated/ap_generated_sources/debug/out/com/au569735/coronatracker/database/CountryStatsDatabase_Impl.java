package com.au569735.coronatracker.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CountryStatsDatabase_Impl extends CountryStatsDatabase {
  private volatile CountryStatisticDao _countryStatisticDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CountryStatistic` (`uid` INTEGER NOT NULL, `country` TEXT, `countryCode` TEXT, `flagIconId` INTEGER NOT NULL, `cases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `rating` REAL NOT NULL, `note` TEXT, PRIMARY KEY(`uid`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '302e049eb603805e127ee39575cf982c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `CountryStatistic`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCountryStatistic = new HashMap<String, TableInfo.Column>(8);
        _columnsCountryStatistic.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("countryCode", new TableInfo.Column("countryCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("flagIconId", new TableInfo.Column("flagIconId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("cases", new TableInfo.Column("cases", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountryStatistic.put("note", new TableInfo.Column("note", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCountryStatistic = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCountryStatistic = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCountryStatistic = new TableInfo("CountryStatistic", _columnsCountryStatistic, _foreignKeysCountryStatistic, _indicesCountryStatistic);
        final TableInfo _existingCountryStatistic = TableInfo.read(_db, "CountryStatistic");
        if (! _infoCountryStatistic.equals(_existingCountryStatistic)) {
          return new RoomOpenHelper.ValidationResult(false, "CountryStatistic(com.au569735.coronatracker.model.CountryStatistic).\n"
                  + " Expected:\n" + _infoCountryStatistic + "\n"
                  + " Found:\n" + _existingCountryStatistic);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "302e049eb603805e127ee39575cf982c", "943ac025bd87cf4cc6a4688b1283edfc");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "CountryStatistic");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `CountryStatistic`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CountryStatisticDao CountryStatisticDao() {
    if (_countryStatisticDao != null) {
      return _countryStatisticDao;
    } else {
      synchronized(this) {
        if(_countryStatisticDao == null) {
          _countryStatisticDao = new CountryStatisticDao_Impl(this);
        }
        return _countryStatisticDao;
      }
    }
  }
}
