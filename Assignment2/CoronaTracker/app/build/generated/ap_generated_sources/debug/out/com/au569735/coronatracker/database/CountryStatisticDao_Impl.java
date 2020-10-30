package com.au569735.coronatracker.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.au569735.coronatracker.model.CountryStatistic;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CountryStatisticDao_Impl implements CountryStatisticDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CountryStatistic> __insertionAdapterOfCountryStatistic;

  private final EntityInsertionAdapter<CountryStatistic> __insertionAdapterOfCountryStatistic_1;

  private final EntityDeletionOrUpdateAdapter<CountryStatistic> __deletionAdapterOfCountryStatistic;

  private final EntityDeletionOrUpdateAdapter<CountryStatistic> __updateAdapterOfCountryStatistic;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CountryStatisticDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCountryStatistic = new EntityInsertionAdapter<CountryStatistic>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CountryStatistic` (`uid`,`country`,`countryCode`,`flagIconId`,`cases`,`deaths`,`rating`,`note`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CountryStatistic value) {
        stmt.bindLong(1, value.getUid());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        if (value.getCountryCode() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCountryCode());
        }
        stmt.bindLong(4, value.getFlagIconId());
        stmt.bindLong(5, value.getCases());
        stmt.bindLong(6, value.getDeaths());
        stmt.bindDouble(7, value.getRating());
        if (value.getNote() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getNote());
        }
      }
    };
    this.__insertionAdapterOfCountryStatistic_1 = new EntityInsertionAdapter<CountryStatistic>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CountryStatistic` (`uid`,`country`,`countryCode`,`flagIconId`,`cases`,`deaths`,`rating`,`note`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CountryStatistic value) {
        stmt.bindLong(1, value.getUid());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        if (value.getCountryCode() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCountryCode());
        }
        stmt.bindLong(4, value.getFlagIconId());
        stmt.bindLong(5, value.getCases());
        stmt.bindLong(6, value.getDeaths());
        stmt.bindDouble(7, value.getRating());
        if (value.getNote() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getNote());
        }
      }
    };
    this.__deletionAdapterOfCountryStatistic = new EntityDeletionOrUpdateAdapter<CountryStatistic>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CountryStatistic` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CountryStatistic value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__updateAdapterOfCountryStatistic = new EntityDeletionOrUpdateAdapter<CountryStatistic>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CountryStatistic` SET `uid` = ?,`country` = ?,`countryCode` = ?,`flagIconId` = ?,`cases` = ?,`deaths` = ?,`rating` = ?,`note` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CountryStatistic value) {
        stmt.bindLong(1, value.getUid());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        if (value.getCountryCode() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCountryCode());
        }
        stmt.bindLong(4, value.getFlagIconId());
        stmt.bindLong(5, value.getCases());
        stmt.bindLong(6, value.getDeaths());
        stmt.bindDouble(7, value.getRating());
        if (value.getNote() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getNote());
        }
        stmt.bindLong(9, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CountryStatistic";
        return _query;
      }
    };
  }

  @Override
  public void addStatistic(final CountryStatistic stat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountryStatistic.insert(stat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void addAll(final CountryStatistic... stats) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountryStatistic_1.insert(stats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteStatistic(final CountryStatistic stat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCountryStatistic.handle(stat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateStatistic(final CountryStatistic stat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCountryStatistic.handle(stat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<CountryStatistic>> getAll() {
    final String _sql = "SELECT * FROM CountryStatistic";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"CountryStatistic"}, false, new Callable<List<CountryStatistic>>() {
      @Override
      public List<CountryStatistic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
          final int _cursorIndexOfFlagIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "flagIconId");
          final int _cursorIndexOfCases = CursorUtil.getColumnIndexOrThrow(_cursor, "cases");
          final int _cursorIndexOfDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "deaths");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<CountryStatistic> _result = new ArrayList<CountryStatistic>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CountryStatistic _item;
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpCountryCode;
            _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
            final int _tmpFlagIconId;
            _tmpFlagIconId = _cursor.getInt(_cursorIndexOfFlagIconId);
            final int _tmpCases;
            _tmpCases = _cursor.getInt(_cursorIndexOfCases);
            final int _tmpDeaths;
            _tmpDeaths = _cursor.getInt(_cursorIndexOfDeaths);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _item = new CountryStatistic(_tmpCountry,_tmpCountryCode,_tmpFlagIconId,_tmpCases,_tmpDeaths,_tmpRating,_tmpNote);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public CountryStatistic findCountryStatistic(final int uid) {
    final String _sql = "SELECT * FROM CountryStatistic WHERE uid LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, uid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
      final int _cursorIndexOfFlagIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "flagIconId");
      final int _cursorIndexOfCases = CursorUtil.getColumnIndexOrThrow(_cursor, "cases");
      final int _cursorIndexOfDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "deaths");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final CountryStatistic _result;
      if(_cursor.moveToFirst()) {
        final String _tmpCountry;
        _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        final String _tmpCountryCode;
        _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
        final int _tmpFlagIconId;
        _tmpFlagIconId = _cursor.getInt(_cursorIndexOfFlagIconId);
        final int _tmpCases;
        _tmpCases = _cursor.getInt(_cursorIndexOfCases);
        final int _tmpDeaths;
        _tmpDeaths = _cursor.getInt(_cursorIndexOfDeaths);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        final String _tmpNote;
        _tmpNote = _cursor.getString(_cursorIndexOfNote);
        _result = new CountryStatistic(_tmpCountry,_tmpCountryCode,_tmpFlagIconId,_tmpCases,_tmpDeaths,_tmpRating,_tmpNote);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _result.setUid(_tmpUid);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CountryStatistic findCountryStatistic(final String country) {
    final String _sql = "SELECT * FROM CountryStatistic WHERE country LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
      final int _cursorIndexOfFlagIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "flagIconId");
      final int _cursorIndexOfCases = CursorUtil.getColumnIndexOrThrow(_cursor, "cases");
      final int _cursorIndexOfDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "deaths");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final CountryStatistic _result;
      if(_cursor.moveToFirst()) {
        final String _tmpCountry;
        _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        final String _tmpCountryCode;
        _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
        final int _tmpFlagIconId;
        _tmpFlagIconId = _cursor.getInt(_cursorIndexOfFlagIconId);
        final int _tmpCases;
        _tmpCases = _cursor.getInt(_cursorIndexOfCases);
        final int _tmpDeaths;
        _tmpDeaths = _cursor.getInt(_cursorIndexOfDeaths);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        final String _tmpNote;
        _tmpNote = _cursor.getString(_cursorIndexOfNote);
        _result = new CountryStatistic(_tmpCountry,_tmpCountryCode,_tmpFlagIconId,_tmpCases,_tmpDeaths,_tmpRating,_tmpNote);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _result.setUid(_tmpUid);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
