const sql = require('mssql')

async function teste() {
  try {
    await sql.connect('mssql://testeLogon:sample123@localhost:1433/SampleDatabase');

    const result = await sql.query(`select * from Pessoa`);

    console.dir(result)
  } catch (err) {
    console.dir("ERRO");
  }
}

teste();