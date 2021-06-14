db.createUser({
    user: 'root',
    pwd: 'toor',
    roles: [
        {
            role: 'readWrite',
            db: 'alelo-frota',
        },
    ],
});

db = new Mongo().getDB("alelo-frota");

db.createCollection('vehicle_sequence', { capped: false });
db.createCollection('vehicle', { capped: false });

db.test.insert([
    { "item": 1 },
    { "item": 2 },
    { "item": 3 },
    { "item": 4 },
    { "item": 5 }
]);